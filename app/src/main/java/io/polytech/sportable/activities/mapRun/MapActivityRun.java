package io.polytech.sportable.activities.mapRun;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.freerun.RunViewModel;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

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