package com.example.harjoitusty;

import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherDataRetriever {
    private final String API_KEY = "59c1efc0e841d35902c2abee9ac0136e";
    private final String CONVERTER_BASED_URL = "https://api.openweathermap.org/geo/1.0/direct?q=%s&limit=&appid=%s";
    private final String WEATHER_BASED_URL = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s";

    public WeatherData getWeatherData(String municipality) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode areas = null;
            areas = objectMapper.readTree(new URL(String.format(CONVERTER_BASED_URL, municipality, API_KEY)));
            Log.d("LUT", areas.toPrettyString());
            if (areas == null || areas.isEmpty()) {
                System.out.println("No geographic data returned for the municipality: " + municipality);
                return null;
            }

            String latitude = areas.get(0).get("lat").toString();
            String longitude = areas.get(0).get("lon").toString();

            System.out.println("Fetching weather data for coordinates: " + latitude + ", " + longitude);

            JsonNode weatherData;

            weatherData = objectMapper.readTree(new URL(String.format(WEATHER_BASED_URL, latitude, longitude, API_KEY)));
            Log.d("LUT", weatherData.toPrettyString());
            if (weatherData == null || weatherData.isEmpty()) {
                System.out.println("No weather data returned for the coordinates: " + latitude + ", " + longitude);
                return null;
            }
            if (!weatherData.has("weather") || !weatherData.get("weather").isArray() || weatherData.get("weather").isEmpty()) {
                System.out.println("Weather data array is missing or empty.");
                return null;
            }

            WeatherData wd = new WeatherData(
                    weatherData.get("name").asText(),
                    weatherData.get("weather").get(0).get("main").asText(),
                    weatherData.get("weather").get(0).get("description").asText(),
                    weatherData.get("main").get(0).get("temp").asText(),
                    weatherData.get("wind").get(0).get("speed").asText()
            );
            return wd;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Bad URL", e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch weather data", e);
        }
    }
}
