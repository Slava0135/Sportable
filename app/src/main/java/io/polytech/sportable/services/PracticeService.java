package io.polytech.sportable.services;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class PracticeService extends Service {

    private FusedLocationProviderClient mFusedLocationClient;

    private IBinder mBinder = new PracticeBinder();

    private final int delay = 10000;

    private long mStartTimeMillis;
    private volatile int distance;

    private Location mLocation;

    public class PracticeBinder extends Binder {

        PracticeService getService() {
            return PracticeService.this;
        }

        public int getDistanceRunning() {
            return distance;
        }

        public long getTimeRunning() {
            return System.currentTimeMillis() - mStartTimeMillis;
        }

    }

    @Override
    public void onCreate() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLocation = mFusedLocationClient.getLastLocation().getResult();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        run();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private void run() {
        mStartTimeMillis = System.currentTimeMillis();
        distance = 0;
        new Thread(() -> {
            while (true) {
                try {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Location newLocation = mFusedLocationClient.getLastLocation().getResult();
                    distance += mLocation.distanceTo(newLocation);
                    mLocation = newLocation;
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}