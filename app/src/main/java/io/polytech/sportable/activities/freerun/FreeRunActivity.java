package io.polytech.sportable.activities.freerun;

import androidx.appcompat.app.AppCompatActivity;

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
import io.polytech.sportable.services.PracticeService;

public class FreeRunActivity extends AppCompatActivity {

    boolean isRunning;
    PracticeService mService;
    boolean mBound = false;

    @SuppressLint({"SetTextI18n", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_run);
        isRunning = true;
        final Button buttonPause = findViewById(R.id.buttonPause);
        buttonPause.setOnClickListener(v -> {
            if (isRunning) {
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
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }


    @Override
    public void onPause() {
        isRunning = false;
        super.onPause();
    }

    @Override
    public void onResume() {
        isRunning = true;
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent stats = new Intent(FreeRunActivity.this, FreeRunStatActivity.class);
        stats.putExtra("distance", mService.getDistanceMeters());
        stats.putExtra("time", mService.getTimeSeconds());
        stats.putExtra("calories", mService.getCalories());
        stats.putExtra("speed", mService.getSpeedMetersPerSecond());
        isRunning = false;
        unbindService(connection);
        mBound = false;
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
                if (mBound){
                    int seconds = mService.getTimeSeconds();
                    int minutes = seconds / 60;
                    if (isRunning) {
                        String time = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

                        valueTime.setText(time);
                        valueDistance.setText((int) mService.getDistanceMeters());
                        valueSpeed.setText((int) mService.getSpeedMetersPerSecond());
                        valueCalories.setText((int) mService.getCalories());
                    }
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            PracticeService.PracticeBinder binder = (PracticeService.PracticeBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };


}