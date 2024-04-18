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
        String location = editTextLocation.getText().toString();

        ExecutorService service = Executors.newSingleThreadExecutor();

        service.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<MunicipalityData> populationData = dr.getData(context, location);
                WeatherData weatherData = wr.getWeatherData(location);
                if (populationData != null && weatherData != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this, MunicipalityDataActivity.class);
                            intent.putExtra("populationData", populationData);
                            intent.putExtra("weatherData", weatherData);
                            startActivity(intent);

                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                Log.d("LUT", "Data haettu");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mla.notifyDataSetChanged();
    }
}
