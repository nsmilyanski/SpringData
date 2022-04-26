package exam.service.impl;

import exam.model.dto.TownRoodSeedDto;
import exam.model.dto.TownSeedDto;
import exam.model.entity.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.ValidatorUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {
    private static final String TOWN_PATH = "src/main/resources/files/xml/towns.xml";

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidatorUtil validatorUtil;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidatorUtil validatorUtil) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWN_PATH));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        xmlParser.fromFile(TOWN_PATH, TownRoodSeedDto.class)
                .getTownSeedDtos()
                .stream()
                .filter(town -> {
                    boolean isValid = validatorUtil.isValid(town);
                    sb
                            .append(isValid ? String.format("Successfully imported Town %s", town.getName())
                                    : "Invalid town")
                            .append(System.lineSeparator());

                    return isValid;
                }).map( townSeedDto -> modelMapper.map(townSeedDto, Town.class))
                .forEach(townRepository::save);

        return sb.toString();
    }

    @Override
    public Town findTownByName(String name) {
        return townRepository.findTownByName(name);
    }
}
