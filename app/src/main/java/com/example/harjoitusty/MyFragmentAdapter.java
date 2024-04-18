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
    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,
                             ArrayList<MunicipalityData> populationData, WeatherData weatherData) {
        super(fragmentManager, lifecycle);
        this.populationData = populationData;
        this.weatherData = weatherData;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return PopulationFragment.newInstance(populationData);
            case 1:
                return WeatherFragment.newInstance(weatherData);
            default:
                // Handle other fragments or default case
                return new Fragment(); // or a specific default fragment
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
