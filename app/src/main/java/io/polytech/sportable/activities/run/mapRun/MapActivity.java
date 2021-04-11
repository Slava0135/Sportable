package io.polytech.sportable.activities.run.mapRun;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


import io.polytech.sportable.R;

public class MapActivity extends AppCompatActivity {
    boolean isAutoCreate;
    String[] typesActivity = {"Километры", "Время", "Калории"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        CheckBox checkBox = findViewById(R.id.checkBoxAutoCreate);
        Button buttonStart = findViewById(R.id.buttonStart);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                isAutoCreate = true;
                buttonStart.setText("Побежали!");
            } else {
                isAutoCreate = false;
                buttonStart.setText("Выбрать точку");
            }
        });

        Spinner spinner = findViewById(R.id.spinnerChoose);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typesActivity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        buttonStart.setOnClickListener(v -> {
                    if (isAutoCreate){
                        Intent run = new Intent(MapActivity.this, MapActivityRun.class);
                        startActivity(run);
                    } else {
                        Intent choose = new Intent(MapActivity.this, MapActivityChoose.class);
                        startActivity(choose);
                    }
                }
            );
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}

