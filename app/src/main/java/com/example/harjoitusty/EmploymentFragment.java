package com.example.harjoitusty;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class EmploymentFragment extends Fragment {
    private TextView textEmployment;
    private TextView textSelfSufficiency;
    private ArrayList<EmploymentData> employmentData;


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
            employmentData = (ArrayList<EmploymentData>) getArguments().getSerializable("employmentData");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textEmployment = view.findViewById(R.id.textEmployment);
        textSelfSufficiency = view.findViewById(R.id.textSelfSufficiency);
        if (employmentData != null) {
            displayEmployment(employmentData);
        }
    }

    private void displayEmployment(ArrayList<EmploymentData> employmentData) {
        String s = "";
        if (employmentData != null) {
            for (EmploymentData data : employmentData) {
                s = s + data.getYear() + ": " + data.getEmploymentRate() + "\n";
            }
        }
        textEmployment.setText(s);
    }
}

