package com.example.harjoitusty;

import java.io.Serializable;

public class WeatherData implements Serializable {
    String name;
    String main;
    String description;
    String temperature;
    String windSpeed;
    String feelsLike;
    String pressure;

    public WeatherData(String name, String main, String description, String temperature, String feelsLike, String pressure, String windSpeed) {
        this.name = name;
        this.main = main;
        this.description = description;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
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

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
