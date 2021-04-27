package io.polytech.sportable.activities.settings;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.MainActivity;

public class FirstEntry extends AppCompatActivity {

    SharedPreferences settings;
    boolean flag = false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_entry);

        settings = getSharedPreferences("io.polytech.sportable", MODE_PRIVATE);

        TextView nameView =  findViewById(R.id.nameView);
        String name = settings.getString(ChangeProfile.NAME, "[НЕТ ДАННЫХ]");
        nameView.setText(name);

        TextView heightView = findViewById(R.id.heightView);
        int height = settings.getInt(ChangeProfile.HEIGHT, 0);
        heightView.setText(Integer.toString(height));

        TextView weightView = findViewById(R.id.weightView);
        float weight = settings.getFloat(ChangeProfile.WEIGHT, 0);
        weightView.setText(Float.toString(weight));

        TextView yearView = findViewById(R.id.yearView);
        int year = settings.getInt(ChangeProfile.YEAR, 0);
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
            prefEditor.putString(ChangeProfile.NAME, name);
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
                prefEditor.putInt(ChangeProfile.HEIGHT, height);
                prefEditor.apply();

                TextView heightView = findViewById(R.id.heightView);
                heightView.setText(Integer.toString(height));
                heightBox.setText(null);

                flag = true;
            } catch (NumberFormatException e) {
                heightBox.setText(null);
            }
        }

        if (!weightBox.getText().toString().equals("")) {
            try {
                float weight = Float.parseFloat(weightBox.getText().toString());
                if (weight < 20.0 || weight > 200.0) throw new NumberFormatException();
                SharedPreferences.Editor prefEditor = settings.edit();
                prefEditor.putFloat(ChangeProfile.WEIGHT, weight);
                prefEditor.apply();

                TextView weightView = findViewById(R.id.weightView);
                weightView.setText(Float.toString(weight));
                weightBox.setText(null);

                flag = true;
            } catch (NumberFormatException e) {
                weightBox.setText(null);
            }
        }

        if (!yearBox.getText().toString().equals("")) {
            try {
                int year = Integer.parseInt(yearBox.getText().toString());
                if (year < 1900 || year > 2015) throw new NumberFormatException();
                SharedPreferences.Editor prefEditor = settings.edit();
                prefEditor.putInt(ChangeProfile.YEAR, year);
                prefEditor.apply();

                TextView yearView = findViewById(R.id.yearView);
                yearView.setText(Integer.toString(year));
                yearBox.setText(null);

                flag = true;
            } catch (NumberFormatException e) {
                yearBox.setText(null);
            }
        }
        tryToQuit();
    }

    public void tryToQuit() {
        if (flag) {
            Toast.makeText(this, "Беги, " + settings.getString(ChangeProfile.NAME,
                    "[ДАННЫЕ УДАЛЕНЫ]") + ", беги!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(FirstEntry.this, MainActivity.class);
            startActivity(intent);
        }
        else Toast.makeText(this, "Введены некорректные данные!", Toast.LENGTH_SHORT).show();
    }
}
