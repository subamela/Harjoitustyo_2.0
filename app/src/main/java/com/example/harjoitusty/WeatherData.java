package com.example.harjoitusty;

import java.io.Serializable;

public class WeatherData implements Serializable {
    String name;
    String main;
    String description;
    String temperature;
    String windSpeed;

    public WeatherData(String name, String main, String description, String temperature, String windSpeed) {
        this.name = name;
        this.main = main;
        this.description = description;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }
}
