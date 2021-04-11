package io.polytech.sportable.activities.run.mapRun;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.run.RunStatActivity;
import io.polytech.sportable.activities.run.freerun.FreeRunActivity;
import io.polytech.sportable.persistence.PracticeResult;
import io.polytech.sportable.services.PracticeService;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;

import java.util.Locale;

public class MapActivityRun extends AppCompatActivity {

    MapViewModel model;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey("18400120-4838-48c7-905a-b2abc915eab0");
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_map_run);
        model = new ViewModelProvider(this).get(MapViewModel.class);
        model.isRunning = true;
        model.locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        model.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                2000, 25, locationListener);
        model.mapView = findViewById(R.id.mapview);

        final Button buttonPause = findViewById(R.id.mapButtonPause);
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

        final Button buttonStop = findViewById(R.id.mapButtonStop);
        buttonStop.setOnClickListener(v -> {
            Intent stats = new Intent(MapActivityRun.this, RunStatActivity.class);
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
    }

    @Override
    protected void onStop() {
        super.onStop();
        model.mapView.onStop();
        MapKitFactory.getInstance().onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        model.mapView.onStart();
        MapKitFactory.getInstance().onStart();
    }

    LocationListener locationListener = location -> model.mapView.getMap().move(
            new CameraPosition(new Point(location.getLatitude(), location.getLongitude()), 17, 0, 0),
            new Animation(Animation.Type.SMOOTH, 1),
            null
            );
}