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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import io.polytech.sportable.R;

public class MapActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;

    boolean autoCreate;

    String[] typesActivity = {"Метры", "Время", "Калории"};

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
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
            TextView num = findViewById(R.id.inputNumber);
            CharSequence text = num.getText();
            if (text.length() <= 0) return;
            float distance = Float.parseFloat(text.toString());
            if (autoCreate) {
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, location -> {
                            if (location != null) {
                                Intent preview = new Intent(MapActivity.this, MapPreviewActivity.class);
                                preview.putExtra("distance", distance);
                                preview.putExtra("latitude", location.getLatitude());
                                preview.putExtra("longitude", location.getLongitude());
                                startActivity(preview);
                                finish();
                            }
                        })
                        .addOnFailureListener(this, e -> Toast.makeText(MapActivity.this, "Не удалось получить местоположение", Toast.LENGTH_SHORT).show());
            } else {
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, location -> {
                            if (location != null) {
                                Intent choose = new Intent(MapActivity.this, MapChooseActivity.class);
                                choose.putExtra("distance", distance);
                                choose.putExtra("latitude", location.getLatitude());
                                choose.putExtra("longitude", location.getLongitude());
                                startActivity(choose);
                            }
                        })
                        .addOnFailureListener(this, e -> Toast.makeText(MapActivity.this, "Не удалось получить местоположение", Toast.LENGTH_SHORT).show());
            }
        });
    }
}

