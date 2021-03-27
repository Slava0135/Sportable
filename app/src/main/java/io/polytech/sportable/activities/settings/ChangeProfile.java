package io.polytech.sportable.activities.settings;

import android.annotation.SuppressLint;
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

    SharedPreferences settings;
    boolean flag = true;

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

    @SuppressLint("SetTextI18n")
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
            try {
                int height = Integer.parseInt(heightBox.getText().toString());
                if (height < 50 || height > 300) throw new NumberFormatException();

                SharedPreferences.Editor prefEditor = settings.edit();
                prefEditor.putInt(HEIGHT, height);
                prefEditor.apply();

                TextView heightView = findViewById(R.id.heightView);
                heightView.setText(Integer.toString(height));
                heightBox.setText(null);

            } catch (NumberFormatException e) {
                heightBox.setText(null);
                flag = false;
            }
        }

        if (!weightBox.getText().toString().equals("")) {
            try {
                float weight = Float.parseFloat(weightBox.getText().toString());
                if (weight < 20.0 || weight > 200.0) throw new NumberFormatException();
                SharedPreferences.Editor prefEditor = settings.edit();
                prefEditor.putFloat(WEIGHT, weight);
                prefEditor.apply();

                TextView weightView = findViewById(R.id.weightView);
                weightView.setText(Float.toString(weight));
                weightBox.setText(null);
            } catch (NumberFormatException e) {
                weightBox.setText(null);
                flag = false;
            }
        }

        if (!yearBox.getText().toString().equals("")) {
            try {
                int year = Integer.parseInt(yearBox.getText().toString());
                if (year < 1900 || year > 2015) throw new NumberFormatException();
                SharedPreferences.Editor prefEditor = settings.edit();
                prefEditor.putInt(YEAR, year);
                prefEditor.apply();

                TextView yearView = findViewById(R.id.yearView);
                yearView.setText(Integer.toString(year));
                yearBox.setText(null);
            } catch (NumberFormatException e) {
                yearBox.setText(null);
                flag = false;
            }
        }
        tryToQuit();
    }

    public void tryToQuit() {
        if (flag) {
            Toast.makeText(this, "Вы выходите из настроек профиля!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ChangeProfile.this, SettingsActivity.class);
            startActivity(intent);
        }
        else Toast.makeText(this, "Введены некорректные данные!", Toast.LENGTH_SHORT).show();
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