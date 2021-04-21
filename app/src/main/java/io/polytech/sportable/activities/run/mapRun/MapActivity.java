package io.polytech.sportable.activities.run.mapRun;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.run.freerun.RunViewModel;
import io.polytech.sportable.models.practice.PracticeType;

public class MapActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    MapViewModel model;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        model = new ViewModelProvider(this).get(MapViewModel.class);

        model.practiceType = PracticeType.valueOf(((String) getIntent().getExtras().get("activity_type")));

        model.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        CheckBox checkBox = findViewById(R.id.checkBoxAutoCreate);
        Button buttonStart = findViewById(R.id.buttonStart);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                model.autoCreate = true;
                buttonStart.setText("Побежали!");
            } else {
                model.autoCreate = false;
                buttonStart.setText("Выбрать точку");
            }
        });

        Spinner spinner = findViewById(R.id.spinnerChoose);
        ArrayAdapter<MapViewModel.Units> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, MapViewModel.Units.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        buttonStart.setOnClickListener(v -> {
            TextView num = findViewById(R.id.inputNumber);
            CharSequence text = num.getText();
            if (text.length() <= 0) return;
            float distance = model.unit.calculateDistance(Float.parseFloat(text.toString()));
            if (model.autoCreate) {
                model.fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, location -> {
                            if (location != null) {
                                Intent preview = new Intent(MapActivity.this, MapPreviewActivity.class);
                                preview.putExtra("distance", distance);
                                preview.putExtra("latitude", location.getLatitude());
                                preview.putExtra("longitude", location.getLongitude());
                                preview.putExtra("activity_type", model.practiceType.toString());
                                startActivity(preview);
                                finish();
                            }
                        })
                        .addOnFailureListener(this, e -> Toast.makeText(MapActivity.this, "Не удалось получить местоположение", Toast.LENGTH_SHORT).show());
            } else {
                model.fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, location -> {
                            if (location != null) {
                                Intent choose = new Intent(MapActivity.this, MapChooseActivity.class);
                                choose.putExtra("distance", distance);
                                choose.putExtra("latitude", location.getLatitude());
                                choose.putExtra("longitude", location.getLongitude());
                                choose.putExtra("activity_type", model.practiceType.toString());
                                startActivity(choose);
                            }
                        })
                        .addOnFailureListener(this, e -> Toast.makeText(MapActivity.this, "Не удалось получить местоположение", Toast.LENGTH_SHORT).show());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        model.unit = (MapViewModel.Units) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}

