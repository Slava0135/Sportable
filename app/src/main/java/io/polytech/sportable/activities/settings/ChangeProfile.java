package io.polytech.sportable.activities.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import io.polytech.sportable.R;

public class ChangeProfile extends AppCompatActivity {

    public static final String NAME = "Name";
    private static final String HEIGHT = "0";
    private static final String WEIGHT = "0.0";
    private static final String YEAR = "1900";
    //private static final String SEX = "M";


    SharedPreferences settings;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_profile);

        settings = getSharedPreferences("io.polytech.sportable", MODE_PRIVATE);

        TextView nameView =  findViewById(R.id.nameView);
        String name = settings.getString(NAME, "user");
        nameView.setText(name);

        TextView heightView = findViewById(R.id.heightView);
        int height = settings.getInt(HEIGHT, 0);
        heightView.setText(Integer.toString(height));

        TextView weightView = findViewById(R.id.weightView);
        float weight = settings.getFloat(WEIGHT, 0);
        weightView.setText(Float.toString(weight));

        TextView yearView = findViewById(R.id.yearView);
        int year = settings.getInt(YEAR, 1900);
        yearView.setText(Integer.toString(year));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void saveAndQuit(View view) {

        EditText nameBox = findViewById(R.id.nameBox);
        EditText heightBox = findViewById(R.id.heightBox);
        EditText weightBox = findViewById(R.id.weightBox);
        EditText yearBox = findViewById(R.id.yearBox);

        if (!nameBox.getText().toString().equals("")) {
            String name = nameBox.getText().toString();
            SharedPreferences.Editor prefEditor = settings.edit();
            prefEditor.putString(NAME, name);
            prefEditor.apply();

            TextView nameView = findViewById(R.id.nameView);
            nameView.setText(name);
            nameBox.setText(null);
        }

        if (!heightBox.getText().toString().equals("")) {
            int height = Integer.parseInt(heightBox.getText().toString());
            // сохраняем его в настройках
            SharedPreferences.Editor prefEditor = settings.edit();
            prefEditor.putInt(HEIGHT, height);
            prefEditor.apply();

            TextView heightView = findViewById(R.id.heightView);
            heightView.setText(Integer.toString(height));
            heightBox.setText(null);
        }

        if (!weightBox.getText().toString().equals("")) {
            float weight = Float.parseFloat(weightBox.getText().toString());
            // сохраняем его в настройках
            SharedPreferences.Editor prefEditor = settings.edit();
            prefEditor.putFloat(WEIGHT, weight);
            prefEditor.apply();

            TextView weightView = findViewById(R.id.weightView);
            weightView.setText(Float.toString(weight));
            weightBox.setText(null);
        }

        if (!yearBox.getText().toString().equals("")) {
            int year = Integer.parseInt(yearBox.getText().toString());
            SharedPreferences.Editor prefEditor = settings.edit();
            prefEditor.putInt(YEAR, year);
            prefEditor.apply();

            TextView yearView = findViewById(R.id.yearView);
            yearView.setText(Integer.toString(year));
            yearBox.setText(null);
        }

        Toast.makeText(this, "Вы выходите из настроек профиля!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ChangeProfile.this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "Вы выходите из настроек профиля и ничего не сохраняете!",
                    Toast.LENGTH_SHORT).show();
            Intent intentQuit = new Intent(ChangeProfile.this, SettingsActivity.class);
            startActivity(intentQuit);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}