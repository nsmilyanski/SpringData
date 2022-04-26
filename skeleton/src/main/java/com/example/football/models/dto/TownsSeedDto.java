package com.example.football.models.dto;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownsSeedDto {

    @Expose
    private String name;

    @Expose
    private long population;

    @Expose
    private String travelGuide;

    public TownsSeedDto() {
    }

    @Size(min = 2)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Positive
    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    @Size(min = 10)
    @NotNull
    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
