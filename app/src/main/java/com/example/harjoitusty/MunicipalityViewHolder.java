package com.example.harjoitusty;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MunicipalityViewHolder extends RecyclerView.ViewHolder {
    TextView municipalityName, municipalityWeather;
    public MunicipalityViewHolder(@NonNull View itemView) {
        super(itemView);
        municipalityName = itemView.findViewById(R.id.textMunicipalityName2);
        municipalityWeather = itemView.findViewById(R.id.textWeather);

    }
}
