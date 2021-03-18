package io.polytech.sportable.activities.freerun;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.MainActivity;
import io.polytech.sportable.activities.freerun.FreeRunStatActivity;
import io.polytech.sportable.activities.settings.ChangeProfile;
import io.polytech.sportable.activities.settings.SettingsActivity;
import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.persistence.PracticeResult;
import io.polytech.sportable.services.PracticeService;

public class FreeRunActivity extends AppCompatActivity {

    RunViewModel model;
    public boolean isGeoEnabled() {
        Context mContext = getApplicationContext();
        LocationManager mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        boolean mIsGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean mIsNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean mIsGeoDisabled = mIsGPSEnabled && mIsNetworkEnabled;
        return mIsGeoDisabled;
    }

    @SuppressLint({"SetTextI18n", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        model = new ViewModelProvider(this).get(RunViewModel.class);
        setContentView(R.layout.activity_free_run);
        boolean geo = isGeoEnabled();
        if (!isGeoEnabled()) {
            Toast.makeText(this, "Еблан зачем ты зашел в карты без геолокации? Соси хуй еп)", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(FreeRunActivity.this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        model.isRunning = true;
        final Button buttonPause = findViewById(R.id.buttonPause);
        buttonPause.setOnClickListener(v -> {
            if (model.isRunning) {
                buttonPause.setText("continue");
                model.isRunning = false;
                if (model.mBound){
                    model.mService.pause();
                }
            } else {
                model.isRunning = true;
                if (model.mBound){
                    model.mService.resume();
                }
                buttonPause.setText("pause");
            }
        });

        final Button buttonStop = findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(v -> {
            Intent stats = new Intent(FreeRunActivity.this, FreeRunStatActivity.class);
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
                    valueDistance.setText("" + model.mService.getDistanceMeters());
                    valueSpeed.setText("" + model.mService.getSpeedMetersPerSecond());
                    valueCalories.setText("" + model.mService.getCalories());
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}