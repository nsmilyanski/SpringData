package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatSeedRootDto {

    @XmlElement(name = "stat")
    private Set<StatSeedDto> statSeedDtos;


    public Set<StatSeedDto> getStatSeedDtos() {
        return statSeedDtos;
    }

    public void setStatSeedDtos(Set<StatSeedDto> statSeedDtos) {
        this.statSeedDtos = statSeedDtos;
    }
}
