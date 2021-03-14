package io.polytech.sportable.activities.freerun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.freerun.FreeRunStatActivity;
import io.polytech.sportable.models.practice.PracticeType;
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
        model.isRunning = true;
        final Button buttonPause = findViewById(R.id.buttonPause);
        buttonPause.setOnClickListener(v -> {
            if (model.isRunning) {
                buttonPause.setText("continue");
                onPause();
            } else {
                buttonPause.setText("pause");
                onResume();
            }
        });

        final Button buttonStop = findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(v -> onStop());
        runTimer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, PracticeService.class);
        bindService(intent, model.connection, Context.BIND_AUTO_CREATE);
    }


    @Override
    public void onPause() {
        model.isRunning = false;
        if (model.mBound){
            model.mService.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        model.isRunning = true;
        if (model.mBound){
            model.mService.resume();
        }
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent stats = new Intent(FreeRunActivity.this, FreeRunStatActivity.class);
        stats.putExtra("distance", model.mService.getDistanceMeters());
        stats.putExtra("time", model.mService.getTimeSeconds());
        stats.putExtra("calories", model.mService.getCalories());
        stats.putExtra("speed", model.mService.getSpeedMetersPerSecond());
        model.isRunning = false;
        unbindService(model.connection);
        model.mBound = false;
        startActivity(stats);
    }

    public void runTimer() {
        final TextView valueTime = (TextView)findViewById(R.id.valueTime);
        final TextView valueDistance = (TextView)findViewById(R.id.valueDistance);
        final TextView valueSpeed = (TextView)findViewById(R.id.valueSpeed);
        final TextView valueCalories = (TextView)findViewById(R.id.valueCalories);
        final Handler handler = new Handler();
        handler.post(new Runnable() {

            @Override
            public void run() {
                if (model.mBound){
                    int seconds = model.mService.getTimeSeconds();
                    int minutes = seconds / 60;
                    if (model.isRunning) {
                        String time = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

                        valueTime.setText(time);
                        valueDistance.setText((int) model.mService.getDistanceMeters());
                        valueSpeed.setText((int) model.mService.getSpeedMetersPerSecond());
                        valueCalories.setText((int) model.mService.getCalories());
                    }
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }
}