package com.example.harjoitusty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class WeatherFragment extends Fragment {
    private TextView textMunicipalityName2;
    private ImageView weatherIcon;
    private TextView textTemperature;
    private TextView textWeatherData;
    private WeatherData weatherData;

    public static WeatherFragment newInstance(WeatherData weatherData) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putSerializable("weatherData", weatherData);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weatherData = (WeatherData) getArguments().getSerializable("weatherData");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textMunicipalityName2 = view.findViewById(R.id.textMunicipalityName2);
        weatherIcon = view.findViewById(R.id.imageWeatherIcon);
        textTemperature = view.findViewById(R.id.textTemperature);
        textWeatherData = view.findViewById(R.id.textEmployment);

        if (weatherData != null) {
            displayWeather(weatherData);
        }
    }
    public void displayWeather(WeatherData weatherData) {
        if (weatherData != null) {
            updateWeatherIcon(weatherData.getMain());

            double temperatureInCelsius = Double.parseDouble(weatherData.getTemperature()) - 273.15;
            double feelsLikeInCelsius = Double.parseDouble(weatherData.getFeelsLike()) - 273.15;

            textMunicipalityName2.setText(weatherData.getName());
            textTemperature.setText(String.format("%.1f°C", temperatureInCelsius));
            textWeatherData.setText(weatherData.getName() + "\n" +
                    "Sää nyt: " + weatherData.getMain() + " (" + weatherData.getDescription() +")\n" +
                    "Lämpötila: " + String.format("%.1f°C", temperatureInCelsius)  + "\n" +
                    "Tuntuu iholla: " + String.format("%.1f°C", feelsLikeInCelsius) + "\n" +
                    "Ilmanpaine: " + weatherData.getPressure() + "hPa\n" +
                    "Tuulennopeus: " + weatherData.getWindSpeed() + " m/s\n"
            );
        }
    }

    private void updateWeatherIcon(String weatherCondition) {
        ImageView weatherIcon = getView().findViewById(R.id.imageWeatherIcon);
        switch (weatherCondition.toLowerCase()) {
            case "clear":
                weatherIcon.setImageResource(R.drawable.ic_clear_sky);
                break;
            case "rain":
                weatherIcon.setImageResource(R.drawable.ic_rain);
                break;
            case "shower rain":
                weatherIcon.setImageResource(R.drawable.ic_shower_rain);
                break;
            case "few clouds":
                weatherIcon.setImageResource(R.drawable.ic_few_clouds);
                break;
            case "clouds":
                weatherIcon.setImageResource(R.drawable.ic_scattered_clouds);
                break;
            case "broken clouds":
                weatherIcon.setImageResource(R.drawable.ic_broken_clouds);
                break;
            case "snow":
                weatherIcon.setImageResource(R.drawable.ic_snow);
                break;
            case "thunderstorm":
                weatherIcon.setImageResource(R.drawable.ic_thunderstorm);
                break;
            case "mist":
                weatherIcon.setImageResource(R.drawable.ic_mist);
                break;
            default:
                weatherIcon.setImageResource(R.drawable.ic_clear_sky);
        }
    }
}
