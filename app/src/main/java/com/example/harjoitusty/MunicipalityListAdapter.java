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
    private OnMunicipalityClickListener listener;
    public MunicipalityListAdapter(Context context, ArrayList<Municipality> municipalities, OnMunicipalityClickListener listener) {
        this.context = context;
        this.municipalities = municipalities;
        this.listener = listener;
    }
    @NonNull
    @Override
    public MunicipalityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MunicipalityViewHolder(LayoutInflater.from(context).inflate(R.layout.municipality_view_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MunicipalityViewHolder holder, int position) {
        holder.municipalityName.setText(municipalities.get(position).getMunicipalityName());
        holder.municipalityName.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            if (listener != null && position != RecyclerView.NO_POSITION) {
                listener.onMunicipalityClick(municipalities.get(position));
            }
        });
    }
    public interface OnMunicipalityClickListener {
        void onMunicipalityClick(Municipality municipality);
    }

    @Override
    public int getItemCount() {
        return municipalities.size();
    }
}
