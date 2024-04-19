package com.example.harjoitusty;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class EmploymentFragment extends Fragment {
    private TextView textTemperature;
    private TextView textWeatherData;
    private EmploymentData employmentData;


    public EmploymentFragment() {
        // Required empty public constructor
    }


    public static EmploymentFragment newInstance(ArrayList<EmploymentData> employmentData) {
        EmploymentFragment fragment = new EmploymentFragment();
        Bundle args = new Bundle();
        args.putSerializable("employmentData", employmentData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            employmentData = (EmploymentData) getArguments().getSerializable("employmentData");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_employment, container, false);
    }
}