package io.polytech.sportable.activities.run.mapRun;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.run.RunStatActivity;
import io.polytech.sportable.persistence.PracticeResult;
import io.polytech.sportable.services.PracticeService;

import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;

import java.util.Locale;

public class MapRunActivity extends AppCompatActivity implements UserLocationObjectListener {

    MapRunViewModel model;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_map_run);

        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MapRunViewModel.class);
        model.isRunning = true;
        model.mapView = findViewById(R.id.chooseMapview);
        model.mapView.getMap().move(
                new CameraPosition(new Point(0, 0), 17, 0, 0)
        );

        MapKit mapKit = MapKitFactory.getInstance();
        model.userLocationLayer = mapKit.createUserLocationLayer(model.mapView.getMapWindow());
        model.userLocationLayer.setVisible(true);
        model.userLocationLayer.setHeadingEnabled(true);
        model.userLocationLayer.setObjectListener(this);

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
            Intent stats = new Intent(MapRunActivity.this, RunStatActivity.class);
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
        final TextView valueTime = findViewById(R.id.mapValueTime);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (model.mBound && model.isRunning){
                    int seconds = model.mService.getTimeSeconds();
                    int minutes = seconds / 60;
                    String time = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds % 60);
                    valueTime.setText(time);
                }
                handler.postDelayed(this, 1000);
            }
        });
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

    @Override
    public void onObjectAdded(@NonNull UserLocationView userLocationView) {
        model.userLocationLayer.setAnchor(
                new PointF((float)(model.mapView.getWidth() * 0.5), (float)(model.mapView.getHeight() * 0.5)),
                new PointF((float)(model.mapView.getWidth() * 0.5), (float)(model.mapView.getHeight() * 0.5)));
    }

    @Override
    public void onObjectRemoved(@NonNull UserLocationView userLocationView) {
    }

    @Override
    public void onObjectUpdated(@NonNull UserLocationView userLocationView, @NonNull ObjectEvent objectEvent) {
    }
}