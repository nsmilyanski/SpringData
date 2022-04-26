package com.example.football.service.impl;

import com.example.football.models.dto.PlayerSeedDto;
import com.example.football.models.dto.PlayerSeedRootDto;
import com.example.football.models.entity.Player;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidatorUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final String PLAYER_PATH = "src/main/resources/files/xml/players.xml";

    private final PlayerRepository playerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final StatService statService;
    private final TownService townService;
    private final TeamService teamService;

    public PlayerServiceImpl(PlayerRepository playerRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidatorUtil validatorUtil, StatService statService, TownService townService, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.statService = statService;
        this.townService = townService;
        this.teamService = teamService;
    }


    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(Path.of(PLAYER_PATH));
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();



        xmlParser.fromFile(PLAYER_PATH, PlayerSeedRootDto.class)
                .getPlayerSeedDtos()
                .stream()
                .filter(player -> {
                    boolean isValid = validatorUtil.isValid(player);

                    sb.append(isValid ? String.format("Successfully imported Player %s %s - %s",
                            player.getFirstName(), player.getLastName(), player.getPosition()) :
                            String.format("Invalid Player%n"));

                    return isValid;
                }).map(player -> {
                    Player map = modelMapper.map(player, Player.class);
                    map.setTown(townService.findTownByName(player.getTown().getName()));
                    map.setStat(statService.findStatById(player.getStat().getId()));
                    map.setTeam(teamService.findTeamByName(player.getTeam().getName()));

                    return map;
                }).forEach(playerRepository::save);


        return sb.toString();
    }

    @Override
    public String exportBestPlayers() {

        StringBuilder sb = new StringBuilder();

        LocalDate start = LocalDate.of(1995, 01, 01);
        LocalDate end = LocalDate.of(2003, 01, 01);

        List<Player> players =
                playerRepository.findAllByBirthDateBetweenOrderByShootingDesc(start, end);

        for (Player pl: players) {
            sb.append(pl.toString()).append(System.lineSeparator());

        }
        return sb.toString();
    }
}
