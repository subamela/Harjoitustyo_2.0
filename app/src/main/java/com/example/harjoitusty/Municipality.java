package com.example.harjoitusty;

import java.io.Serializable;

public class Municipality implements Serializable {
    private String municipalityName;
    private Integer population;
    private Integer politicalData;
    private Integer weatherInfo;

    public Municipality(String municipalityName, Integer population, Integer politicalData, Integer weatherInfo) {
        this.municipalityName = municipalityName;
        this.population = population;
        this.politicalData = politicalData;
        this.weatherInfo = weatherInfo;
    }

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getPoliticalData() {
        return politicalData;
    }

    public void setPoliticalData(Integer politicalData) {
        this.politicalData = politicalData;
    }

    public Integer getWeather() {
        return weatherInfo;
    }

    public void setWeather(Integer weatherInfo) {
        this.weatherInfo = weatherInfo;
    }
}
