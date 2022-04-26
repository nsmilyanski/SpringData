package com.example.football.service.impl;

import com.example.football.models.dto.StatSeedDto;
import com.example.football.models.dto.StatSeedRootDto;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidatorUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {

    private static final String STAT_PATH = "src/main/resources/files/xml/stats.xml";

    private final StatRepository statRepository;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    public StatServiceImpl(StatRepository statRepository, ValidatorUtil validatorUtil, XmlParser xmlParser, ModelMapper modelMapper) {
        this.statRepository = statRepository;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean areImported() {
        return statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(Path.of(STAT_PATH));
    }

    @Override
    public String importStats() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();


        xmlParser.fromFile(STAT_PATH, StatSeedRootDto.class)
                .getStatSeedDtos()
                .stream()
                .filter(stat -> {
                    boolean isValid = validatorUtil.isValid(stat);

                    sb.append(isValid ? String.format("Successfully imported Stat %.2f - %.2f - %.2f%n",
                            stat.getPassing(), stat.getShooting(), stat.getEndurance()) :
                            String.format("Invalid Stat!%n"));

                    return isValid;
                }).map(stats -> modelMapper.map(stats, Stat.class))
                .forEach(statRepository::save);

        return sb.toString();
    }

    @Override
    public Stat findStatById(int id) {
        return statRepository.findById(id).orElse(null);
    }
}
