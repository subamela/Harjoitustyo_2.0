package com.example.harjoitusty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;


public class MainActivity extends AppCompatActivity implements MunicipalityListAdapter.OnMunicipalityClickListener {
    private MunicipalityStorage municipalityList;
    private EditText editTextLocation;
    private RecyclerView recyclerView;
    private MunicipalityListAdapter mla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        municipalityList = MunicipalityStorage.getInstance();
        editTextLocation = findViewById(R.id.txtEditLocation);
        recyclerView = findViewById(R.id.rvMunicipalities);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mla = new MunicipalityListAdapter(getApplicationContext(), municipalityList.getMunicipalities(),this));

    }
    public void onFindBtnClick(View view) {
        Log.d("LUT", "NNappula toimii");
        Context context = this;
        MunicipalityDataRetriever dr = new MunicipalityDataRetriever();
        WeatherDataRetriever wr = new WeatherDataRetriever();
        EmploymentDataRetriever er = new EmploymentDataRetriever();
        SufficiencyDataRetriever sr = new SufficiencyDataRetriever();
        String location = editTextLocation.getText().toString().trim();
        location = StringUtils.capitalize(location);
        Municipality newMunicipality = new Municipality(location);
        municipalityList.addMunicipality(newMunicipality);
        mla.notifyDataSetChanged();

        ExecutorService service = Executors.newSingleThreadExecutor();

        String finalLocation = location;
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<MunicipalityData> populationData = dr.getData(context, finalLocation);
                    WeatherData weatherData = wr.getWeatherData(finalLocation);
                    ArrayList<EmploymentData> employmentData = er.getData(context, finalLocation);
                    ArrayList<SufficiencyData> sufficiencyData = sr.getData(context, finalLocation);

                    if (populationData != null && weatherData != null && employmentData != null && sufficiencyData != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, MunicipalityDataActivity.class);
                                intent.putExtra("populationData", populationData);
                                intent.putExtra("weatherData", weatherData);
                                intent.putExtra("employmentData", employmentData);
                                intent.putExtra("sufficiencyData", sufficiencyData);
                                Log.d("LUT", "Starting MunicipalityDataActivity with Intent.");
                                startActivity(intent);

                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("LUT", "Error in data retrieval");
                            }
                        });
                    }
                    Log.d("LUT", "Data haettu");
                } catch (Exception e) {
                    Log.e("LUT", "Error in data retrieval: " + e.getMessage());
                }
            }
        });
    }
    public void addMunicipality(View view) {
        String municipalityName = editTextLocation.getText().toString().trim();
        municipalityName = StringUtils.capitalize(municipalityName);
        Municipality newMunicipality = new Municipality(municipalityName);
        MunicipalityStorage.getInstance().addMunicipality(newMunicipality);

    }
    public void onMunicipalityClick(Municipality municipality) {
        editTextLocation.setText(municipality.getMunicipalityName());
        onFindBtnClick(null);

    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.setAdapter(new MunicipalityListAdapter(getApplicationContext(), municipalityList.getMunicipalities(), this));
        //mla.notifyDataSetChanged();
    }
}
