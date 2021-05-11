package io.polytech.sportable.activities.run.freerun;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.MainActivity;
import io.polytech.sportable.activities.run.RunStatActivity;
import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.persistence.PracticeResult;
import io.polytech.sportable.services.PracticeService;

public class FreeRunActivity extends AppCompatActivity {

    RunViewModel model;

    @SuppressLint({"SetTextI18n", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        model = new ViewModelProvider(this).get(RunViewModel.class);
        setContentView(R.layout.activity_free_run);

        ImageView backArrow = findViewById(R.id.goBack);
        backArrow.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            finish();
        });

        model.isRunning = true;

        model.practiceType = PracticeType.valueOf(((String) getIntent().getExtras().get("activity_type")));

        final Button buttonPause = findViewById(R.id.buttonPause);
        buttonPause.setOnClickListener(v -> {
            if (model.isRunning) {
                Drawable top = getResources().getDrawable(R.drawable.ic_run);
                buttonPause.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
                model.isRunning = false;
                if (model.mBound){
                    model.mService.pause();
                }
            } else {
                model.isRunning = true;
                if (model.mBound){
                    model.mService.resume();
                }
                Drawable top = getResources().getDrawable(R.drawable.ic_pause);
                buttonPause.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
            }
        });

        final Button buttonStop = findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(v -> {
            Intent stats = new Intent(FreeRunActivity.this, RunStatActivity.class);
            stats.putExtra("distance", model.mService.getDistanceMeters());
            stats.putExtra("time", model.mService.getTimeSeconds());
            stats.putExtra("calories", model.mService.getCalories());
            stats.putExtra("speed", model.mService.getSpeedMetersPerSecond());
            model.insertRecord(new PracticeResult(
                    System.currentTimeMillis(),
                    model.mService.getDistanceMeters(),
                    model.mService.getCalories(),
                    model.mService.getTimeSeconds(),
                    model.practiceType));
            startActivity(stats);
            finish();
        });
        Intent intent = new Intent(this, PracticeService.class);
        bindService(intent, model.connection, Context.BIND_AUTO_CREATE);
        runTimer();
    }

    public void runTimer() {
        final TextView valueTime = findViewById(R.id.valueTime);
        final TextView valueDistance = findViewById(R.id.valueDistance);
        final TextView valueSpeed = findViewById(R.id.valueSpeed);
        final TextView valueCalories = findViewById(R.id.valueCalories);
        final Handler handler = new Handler();
        handler.post(new Runnable() {

            @Override
            public void run() {
                if (model.mBound && model.isRunning){
                    int seconds = model.mService.getTimeSeconds();
                    int minutes = seconds / 60;
                    String time = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds % 60);
                    valueTime.setText(time);
                    valueDistance.setText(String.format(Locale.getDefault(), "%.0f", model.mService.getDistanceMeters()));
                    valueSpeed.setText(String.format(Locale.getDefault(), "%.1f", model.mService.getSpeedMetersPerSecond()));
                    valueCalories.setText(String.format(Locale.getDefault(), "%.0f",  + model.mService.getCalories()));
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}