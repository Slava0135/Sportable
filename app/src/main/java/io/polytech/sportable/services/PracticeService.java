package io.polytech.sportable.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import io.polytech.sportable.models.practice.PracticeType;

public class PracticeService extends Service {

    LocationManager locationManager;

    private int time;
    private float distance;
    private PracticeType practiceType;

    private Handler handler;
    private Runnable infoUpdate;
    private boolean isRunning;

    private int infoPeriod = 1000;

    private IBinder mBinder = new PracticeBinder();

    public class PracticeBinder extends Binder {
        public PracticeService getService() {
            return PracticeService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        handler = new Handler();
        locationManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                1000,
                10, locationListener);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @SuppressLint("MissingPermission")
    public void run(PracticeType practiceType) {

        time = 0;
        distance = 0;
        this.practiceType = practiceType;
        isRunning = true;

        infoUpdate = () -> {
            if (isRunning) {
                time += infoPeriod;
            }
            handler.postDelayed(infoUpdate, infoPeriod);
        };
        handler.post(infoUpdate);
    }

    public void pause() {
        isRunning = false;
    }

    @SuppressLint("MissingPermission")
    public void resume() {
        isRunning = true;
    }

    public float getDistanceMeters() {
        return distance;
    }
    public int getTimeSeconds() {
        return time / 1000;
    }
    public float getSpeedMetersPerSecond() {
        return 1000 * distance / time;
    }
    public float getCalories() {
        return 0;
    }

    @Override
    public void onDestroy() {
        if (handler != null) {
            handler.removeCallbacks(infoUpdate);
        }
        super.onDestroy();
    }

    LocationListener locationListener = new LocationListener() {

        Location mLocation;

        @Override
        public void onLocationChanged(android.location.Location location) {
            if (mLocation != null) {
                distance += mLocation.distanceTo(location);
            }
            mLocation = location;
        }
    };
}