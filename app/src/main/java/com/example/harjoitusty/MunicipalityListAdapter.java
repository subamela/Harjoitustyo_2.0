package com.example.harjoitusty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MunicipalityListAdapter extends RecyclerView.Adapter<MunicipalityViewHolder> {
    private Context context;
    private ArrayList<Municipality> municipalities = new ArrayList<>();
    public MunicipalityListAdapter(Context context, ArrayList<Municipality> municipalities) {
        this.context = context;
        this.municipalities = municipalities;
    }
    @NonNull
    @Override
    public MunicipalityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MunicipalityViewHolder(LayoutInflater.from(context).inflate(R.layout.municipality_view_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MunicipalityViewHolder holder, int position) {
        holder.municipalityName.setText(municipalities.get(position).getMunicipalityName());
        holder.municipalityWeather.setText(municipalities.get(position).getWeather());

    }

    @Override
    public int getItemCount() {
        return municipalities.size();
    }
}
