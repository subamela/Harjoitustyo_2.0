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


public class MainActivity extends AppCompatActivity {

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
        editTextLocation = findViewById(R.id.txtEditLocation);
        recyclerView = findViewById(R.id.rvMunicipalities);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mla = new MunicipalityListAdapter(getApplicationContext(), MunicipalityStorage.getMunicipalities()));

    }
    public void onFindBtnClick(View view) {
        Log.d("LUT", "NNappula toimii");
        Context context = this;
        MunicipalityDataRetriever dr = new MunicipalityDataRetriever();
        WeatherDataRetriever wr = new WeatherDataRetriever();
        EmploymentDataRetriever er = new EmploymentDataRetriever();
        String location = editTextLocation.getText().toString().trim();
        location = StringUtils.capitalize(location);

        ExecutorService service = Executors.newSingleThreadExecutor();

        String finalLocation = location;
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<MunicipalityData> populationData = dr.getData(context, finalLocation);
                    WeatherData weatherData = wr.getWeatherData(finalLocation);
                    ArrayList<EmploymentData> employmentData = er.getData(context, finalLocation);
                    if (populationData != null && weatherData != null && employmentData != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, MunicipalityDataActivity.class);
                                intent.putExtra("populationData", populationData);
                                intent.putExtra("weatherData", weatherData);
                                intent.putExtra("employmentData", employmentData);
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

    @Override
    protected void onResume() {
        super.onResume();
        mla.notifyDataSetChanged();
    }
}
