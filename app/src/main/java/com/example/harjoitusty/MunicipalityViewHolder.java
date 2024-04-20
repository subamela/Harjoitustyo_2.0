package com.example.harjoitusty;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MunicipalityViewHolder extends RecyclerView.ViewHolder {
    TextView municipalityName;
    public MunicipalityViewHolder(@NonNull View itemView) {
        super(itemView);
        municipalityName = itemView.findViewById(R.id.textMunicipalityName2);
    }


}
