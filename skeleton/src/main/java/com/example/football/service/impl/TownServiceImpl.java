package com.example.football.service.impl;

import com.example.football.models.dto.TownsSeedDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.ValidatorUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class TownServiceImpl implements TownService {

    private static final String TOWN_PATH = "src/main/resources/files/json/towns.json";

    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    public TownServiceImpl(TownRepository townRepository, Gson gson, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
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
    public String importTowns() throws IOException {

        if (townRepository.count() > 0){
            return "In data base towns already exist!";
        }

        StringBuilder sb = new StringBuilder();

        TownsSeedDto[] townsSeedDtos =
                gson.fromJson(readTownsFileContent(), TownsSeedDto[].class);

        Arrays.stream(townsSeedDtos)
                .filter(towsSeed -> {
                    boolean isValid = validatorUtil.isValid(towsSeed);
                    sb.append(isValid ? String.format("Successfully imported Town %s - %d%n",
                            towsSeed.getName(), towsSeed.getPopulation()) : String.format("Invalid town%n"));

                    return isValid;
                }).map(townsSeedDto -> modelMapper.map(townsSeedDto, Town.class))
                .forEach(townRepository::save);

        return sb.toString();
    }

    @Override
    public Town findTownByName(String townName) {
        return townRepository.findTownsByName(townName);
    }
}
