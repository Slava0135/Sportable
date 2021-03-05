package io.polytech.sportable.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.statistics.StatActivity;
import io.polytech.sportable.activities.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.stats_button).setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), StatActivity.class);
            startActivity(intent);
        });
    }

    public void onClick(View view) {
        Toast.makeText(this, "Вы будете настраивать!", Toast.LENGTH_SHORT).show();
        Intent intentSettings = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intentSettings);
    }
}