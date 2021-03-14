package io.polytech.sportable.services;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.Timer;
import java.util.TimerTask;

import io.polytech.sportable.models.practice.PracticeType;

import static android.content.ContentValues.TAG;

public class PracticeService extends Service {

    private int time;
    private float distance;
    private PracticeType practiceType;

    private Handler handler;
    private Runnable locationUpdate;
    private Runnable infoUpdate;
    private boolean isRunning;

    private int locatePeriod = 10000;
    private int infoPeriod = 1000;

    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLocation;

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

    @Override
    public void onCreate() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        handler = new Handler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public void run(PracticeType practiceType) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        time = 0;
        distance = 0;
        this.practiceType = practiceType;
        isRunning = true;

        handler = new Handler();

        mLocation = mFusedLocationClient.getLastLocation().getResult();
        locationUpdate = () -> {
            if (isRunning) {
                Location newLocation = mFusedLocationClient.getLastLocation().getResult();
                distance += mLocation.distanceTo(newLocation);
                mLocation = newLocation;
                handler.postDelayed(locationUpdate, locatePeriod);
            }
        };
        handler.post(locationUpdate);

        infoUpdate = () -> {
            if (isRunning) {
                time += infoPeriod;
                handler.postDelayed(infoUpdate, infoPeriod);
            }
        };
    }

    public void pause() {
        isRunning = false;
    }

    @SuppressLint("MissingPermission")
    public void resume() {
        isRunning = true;
        mLocation = mFusedLocationClient.getLastLocation().getResult();
    }

    public float getDistanceRunning() {
        return distance;
    }
    public int getTimeRunning() {
        return time;
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
            handler.removeCallbacks(locationUpdate);
        }
        mLocation = null;
        mFusedLocationClient = null;
        super.onDestroy();
    }
}