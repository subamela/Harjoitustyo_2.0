package com.example.harjoitusty;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class PopulationFragment extends Fragment {
    private BarChart barChart;
    private TextView txtMunicipalityName;
    private ArrayList<MunicipalityData> populationData;

    public static PopulationFragment newInstance(ArrayList<MunicipalityData> populationData) {
        PopulationFragment fragment = new PopulationFragment();
        Bundle args = new Bundle();
        args.putSerializable("populationData", populationData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            populationData = (ArrayList<MunicipalityData>) getArguments().getSerializable("populationData");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_population, container, false);
        return view;

    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtMunicipalityName = view.findViewById(R.id.textMunicipalityName);
        barChart = view.findViewById(R.id.barChart);
        if (populationData != null && !populationData.isEmpty()) {
            updateUI();
        }
    }
    private void updateUI() {

        Collections.sort(populationData, new Comparator<MunicipalityData>() {
            @Override
            public int compare(MunicipalityData md1, MunicipalityData md2) {
                return Integer.compare(md1.getYear(), md2.getYear());
            }
        });

        ArrayList<BarEntry> entries = new ArrayList<>();
        int size = populationData.size();
        int count = Math.min(size, 5);

        for (int i = size - count; i < size; i++) {
            MunicipalityData data = populationData.get(i);
            entries.add(new BarEntry(data.getYear(), data.getPopulation()));
        }

        BarDataSet barDataSet = new BarDataSet(entries, "Population");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.getDescription().setText("Population over Years");
        barChart.animateY(1500);
        barChart.invalidate();

        if (!entries.isEmpty()) {
            txtMunicipalityName.setText("Population in " + populationData.get(size - 1).getYear());
        }
    }
}
