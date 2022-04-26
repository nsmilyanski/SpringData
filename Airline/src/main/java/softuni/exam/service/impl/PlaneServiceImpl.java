package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PlaneSeedRootDto;
import softuni.exam.models.entities.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
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
public class PlaneServiceImpl implements PlaneService {

    private static final String PLANE_PATH = "src/main/resources/files/xml/planes.xml";

    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public PlaneServiceImpl(PlaneRepository planeRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files.readString(Path.of(PLANE_PATH));
    }

    @Override
    public String importPlanes() throws JAXBException, FileNotFoundException {

        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(PLANE_PATH, PlaneSeedRootDto.class)
                .getPlaneSeedDtos()
                .stream()
                .filter(plane -> {
                    boolean isValid = validationUtil.isValid(plane)
                            && !registerNumberExist(plane.getRegisterNumber());
                    sb.append(isValid ? String.format("Successfully imported Plane %s", plane.getRegisterNumber())
                            : "Invalid Plane");
                    return isValid;
                }).map(plane -> modelMapper.map(plane, Plane.class))
                .forEach(planeRepository::save);
        return sb.toString();
    }

    @Override
    public Plane findByRegisterNumber(String registerNumber) {
        return planeRepository.findPlaneByRegisterNumber(registerNumber);
    }

    private boolean registerNumberExist(String registerNumber) {
        return planeRepository.existsPlaneByRegisterNumber(registerNumber);
    }
}
