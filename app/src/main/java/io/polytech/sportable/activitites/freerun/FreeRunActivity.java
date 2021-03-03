package io.polytech.sportable.activitites.freerun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.polytech.sportable.R;

public class FreeRunActivity extends AppCompatActivity {
    float distance = 0.0f;
    int seconds = 0;
    int calories = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_run);
    }

}