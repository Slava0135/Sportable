package io.polytech.sportable.activities.run.mapRun;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import io.polytech.sportable.R;

public class MapActivity extends AppCompatActivity {

    LocationManager locationManager;
    Location mLocation;

    boolean autoCreate;

    String[] typesActivity = {"Километры", "Время", "Калории"};

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        CheckBox checkBox = findViewById(R.id.checkBoxAutoCreate);
        Button buttonStart = findViewById(R.id.buttonStart);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                autoCreate = true;
                buttonStart.setText("Побежали!");
            } else {
                autoCreate = false;
                buttonStart.setText("Выбрать точку");
            }
        });

        Spinner spinner = findViewById(R.id.spinnerChoose);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typesActivity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        buttonStart.setOnClickListener(v -> {
                    if (autoCreate){
                        if (mLocation == null) {
                            Toast.makeText(this, "Не удалось получить местоположение", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent preview = new Intent(MapActivity.this, MapPreviewActivity.class);
                            preview.putExtra("distance", getDistance());
                            preview.putExtra("latitude", mLocation.getLatitude());
                            preview.putExtra("longitude", mLocation.getLongitude());
                            startActivity(preview);
                            finish();
                        }
                    } else {
                        if (mLocation == null) {
                            Toast.makeText(this, "Не удалось получить местоположение", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent choose = new Intent(MapActivity.this, MapChooseActivity.class);
                            choose.putExtra("distance", getDistance());
                            choose.putExtra("latitude", mLocation.getLatitude());
                            choose.putExtra("longitude", mLocation.getLongitude());
                            startActivity(choose);
                        }
                    }
                }
            );

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                1000, 10, locationListener);
    }

    LocationListener locationListener = location -> mLocation = location;

    float getDistance() {
        return 1000f;
    }
}

