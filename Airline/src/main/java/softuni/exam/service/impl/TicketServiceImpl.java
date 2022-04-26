package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TicketSeedRootDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Plane;
import softuni.exam.models.entities.Ticket;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TicketRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.PlaneService;
import softuni.exam.service.TicketService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private static final String TICKET_PATH ="src/main/resources/files/xml/tickets.xml";

    private final TicketRepository ticketRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TownService townService;
    private final PlaneService planeService;
    private final PassengerService passengerService;

    public TicketServiceImpl(TicketRepository ticketRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, TownService townService, PlaneService planeService, PassengerService passengerService) {
        this.ticketRepository = ticketRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townService = townService;
        this.planeService = planeService;
        this.passengerService = passengerService;
    }

    @Override
    public boolean areImported() {
        return ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files.readString(Path.of(TICKET_PATH));
    }

    @Override
    public String importTickets() throws JAXBException, FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        xmlParser.fromFile(TICKET_PATH, TicketSeedRootDto.class)
                .getTicketSeedDtos()
                .stream()
                .filter(ticket -> {
                    boolean isValid = validationUtil.isValid(ticket)
                            && !serialNumberExist(ticket.getSerialNumber());
                    sb.append(isValid ? String.format("Successfully imported Ticket %s - %s",
                            ticket.getFromTown().getName(), ticket.getToTown().getName())
                            : "Invalid Ticket");
                    return isValid;
                }).map(ticketSeedDto -> {
                    Ticket map = modelMapper.map(ticketSeedDto, Ticket.class);

                    Town townByName = townService.findTownByName(ticketSeedDto.getFromTown().getName());
                    Town townByName1 = townService.findTownByName(ticketSeedDto.getToTown().getName());
                    Passenger passengerByEmail = passengerService.findPassengerByEmail(ticketSeedDto.getPassenger().getEmail());
                    Plane byRegisterNumber = planeService.findByRegisterNumber(ticketSeedDto.getPlane().getRegisterNumber());

                    map.setFromTown(townByName);
                    map.setToTown(townByName1);
                    map.setPassenger(passengerByEmail);
                    map.setPlane(byRegisterNumber);

                    return map;
                }).forEach(ticketRepository::save);


        return sb.toString();
    }

    private boolean serialNumberExist(String serialNumber) {
        return ticketRepository.existsTicketBySerialNumber(serialNumber);
    }
}
