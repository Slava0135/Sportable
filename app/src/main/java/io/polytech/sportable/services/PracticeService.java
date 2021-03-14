package io.polytech.sportable.services;

import android.Manifest;
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

import static android.content.ContentValues.TAG;

public class PracticeService extends Service {

    private long mStartTimeMillis;
    private volatile float distance;

    private Timer timer;
    private UpdateDistanceTask task;

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
        timer = new Timer();
        task = new UpdateDistanceTask();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return super.onStartCommand(intent, flags, startId);
        }
        mStartTimeMillis = System.currentTimeMillis();
        distance = 0;
        mLocation = mFusedLocationClient.getLastLocation().getResult();
        timer.schedule(task, 0, 10000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        timer = null;
        task = null;
        mLocation = null;
        mFusedLocationClient = null;
        super.onDestroy();
    }

    private class UpdateDistanceTask extends TimerTask {
        public void run() {
            if (ActivityCompat.checkSelfPermission(PracticeService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(PracticeService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location newLocation = mFusedLocationClient.getLastLocation().getResult();
            distance += mLocation.distanceTo(newLocation);
            mLocation = newLocation;
            Log.i(TAG, "Distance: " + distance);
            Log.i(TAG, "Time: " + (System.currentTimeMillis() - mStartTimeMillis));
        }
    }

    public float getDistanceRunning() {
        return distance;
    }

    public long getTimeRunning() {
        return System.currentTimeMillis() - mStartTimeMillis;
    }

    public void reset() {
        distance = 0;
        mStartTimeMillis = System.currentTimeMillis();
    }
}