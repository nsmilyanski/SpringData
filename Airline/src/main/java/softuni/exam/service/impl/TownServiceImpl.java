package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownSeedDto;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {

    private static final String TOWN_PATH = "src/main/resources/files/json/towns.json";

    private final Gson gson;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public TownServiceImpl(Gson gson, TownRepository townRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gson = gson;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
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
    public String importTowns() throws IOException {

        StringBuilder sb = new StringBuilder();

        TownSeedDto[] townSeedDtos = gson.fromJson(readTownsFileContent(), TownSeedDto[].class);

        Arrays.stream(townSeedDtos)
                .filter(town -> {
                    boolean isValid = validationUtil.isValid(town)
                            && !nameIsExist(town.getName());

                    sb.append(isValid ? String.format("Successfully imported Town %s - %d",
                                    town.getName(), town.getPopulation()) : "Invalid Town")
                            .append(System.lineSeparator());

                    return isValid;
                }).map(town -> modelMapper.map(town, Town.class))
                .forEach(townRepository::save);

        return sb.toString();
    }

    @Override
    public Town findTownByName(String town) {
        return townRepository.findTownByName(town);
    }

    private boolean nameIsExist(String name) {
        return false;
    }
}
