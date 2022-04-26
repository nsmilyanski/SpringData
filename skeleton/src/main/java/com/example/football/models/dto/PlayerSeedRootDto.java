package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerSeedRootDto {

    @XmlElement(name = "player")
    private Set<PlayerSeedDto> playerSeedDtos;

    public Set<PlayerSeedDto> getPlayerSeedDtos() {
        return playerSeedDtos;
    }

    public void setPlayerSeedDtos(Set<PlayerSeedDto> playerSeedDtos) {
        this.playerSeedDtos = playerSeedDtos;
    }
}
