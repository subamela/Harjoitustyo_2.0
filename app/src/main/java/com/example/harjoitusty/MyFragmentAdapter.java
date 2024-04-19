package com.example.harjoitusty;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class MyFragmentAdapter extends FragmentStateAdapter {
    private ArrayList<MunicipalityData> populationData;
    private WeatherData weatherData;
    private ArrayList<EmploymentData> employmentData;
    private ArrayList<SufficiencyData> sufficiencyData;
    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,
                             ArrayList<MunicipalityData> populationData, WeatherData weatherData, ArrayList<EmploymentData> employmentData, ArrayList<SufficiencyData> sufficiencyData) {
        super(fragmentManager, lifecycle);
        this.populationData = populationData;
        this.weatherData = weatherData;
        this.employmentData = employmentData;
        this.sufficiencyData = sufficiencyData;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return PopulationFragment.newInstance(populationData);
            case 1:
                return WeatherFragment.newInstance(weatherData);
            case 2:
                return  EmploymentFragment.newInstance(employmentData, sufficiencyData);
            default:
                return PopulationFragment.newInstance(populationData);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
