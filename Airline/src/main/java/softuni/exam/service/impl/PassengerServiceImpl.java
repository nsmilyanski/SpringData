package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PassengerSeedDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {

    private static final String PASSENGER_PATH = "src/main/resources/files/json/passengers.json";

    private final PassengerRepository passengerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final TownService townService;

    public PassengerServiceImpl(PassengerRepository passengerRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, TownService townService) {
        this.passengerRepository = passengerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files.readString(Path.of(PASSENGER_PATH));
    }

    @Override
    public String importPassengers() throws IOException {

        StringBuilder sb = new StringBuilder();

        PassengerSeedDto[] passengerSeedDtos = gson.fromJson(readPassengersFileContent(), PassengerSeedDto[].class);

        Arrays.stream(passengerSeedDtos)
                .filter(passenger -> {
                    boolean isValid = validationUtil.isValid(passenger)
                            && !emailIsExist(passenger.getEmail());

                    sb.append(isValid ? String.format("Successfully imported Passenger %s - %s",
                            passenger.getLastName(), passenger.getEmail()) : "Invalid Passenger");

                    return isValid;
                }).map(passenger -> {
                    Passenger map = modelMapper.map(passenger, Passenger.class);

                    Town townByName = townService.findTownByName(passenger.getTown());
                    map.setTown(townByName);
                    return map;
                }).forEach(passengerRepository::save);
        return sb.toString();
    }

    private boolean emailIsExist(String email) {
        return passengerRepository.existsPassengerByEmail(email);
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {

        StringBuilder sb = new StringBuilder();

        List<Passenger> passengers = passengerRepository.findAllPassengersOrderByTicketsDesc();

        for (Passenger p: passengers) {

            sb.append(String.format("""
                    Passenger %s  %s
                    	Email %s
                    Phone - %s
                    	Number of tickets - %d
                    """, p.getFirstName(), p.getLastName(), p.getEmail(), p.getPhoneNumber(),
                    p.getTickets().size()));
        }
        return sb.toString();
    }

    @Override
    public Passenger findPassengerByEmail(String email) {
        return passengerRepository.findPassengerByEmail(email);
    }
}
