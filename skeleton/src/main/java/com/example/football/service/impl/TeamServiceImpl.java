package com.example.football.service.impl;

import com.example.football.models.dto.TeamSeedDto;
import com.example.football.models.entity.Team;
import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidatorUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private static final String TEAMS_PATH = "src/main/resources/files/json/teams.json";

    private final TeamRepository teamRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final TownService townService;

    public TeamServiceImpl(TeamRepository teamRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper, Gson gson, TownService townService) {
        this.teamRepository = teamRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.townService = townService;
    }


    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(Path.of(TEAMS_PATH));
    }

    public String importTeams() throws IOException {
        StringBuilder sb = new StringBuilder();

        TeamSeedDto[] teamSeedDtos = gson.fromJson(readTeamsFileContent(), TeamSeedDto[].class);

        Arrays.stream(teamSeedDtos)
                .filter(teamDto -> {
                    boolean isValid = validatorUtil.isValid(teamDto);
                    sb.append(isValid ? String.format("Successfully imported Team %s - %d%n",
                            teamDto.getName(), teamDto.getFanBase()) : String.format("Invalid Team%n"));
                    return isValid;
                }).map(team -> {
                    Team map = modelMapper.map(team, Team.class);
                    map.setTown(townService.findTownByName(team.getTownName()));

                    return map;
                }).forEach(teamRepository::save);

        return sb.toString();
    }

    @Override
    public Team findTeamByName(String name) {
        return teamRepository.findByName(name);
    }
}
