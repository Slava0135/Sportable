package io.polytech.sportable.activities.run.mapRun;

import android.app.Application;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import androidx.lifecycle.AndroidViewModel;

import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.user_location.UserLocationLayer;

import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.persistence.PracticeRepository;
import io.polytech.sportable.persistence.PracticeResult;
import io.polytech.sportable.services.PracticeService;

public class MapRunViewModel extends AndroidViewModel {

    PracticeType practiceType = PracticeType.Walk;
    boolean isRunning;
    PracticeService mService;
    boolean mBound = false;

    MapObjectCollection mapObjects;

    UserLocationLayer userLocationLayer;
    MapView mapView;
    PracticeRepository repository;

    public MapRunViewModel(Application application) {
        super(application);
        repository = new PracticeRepository(application);
    }

    public void insertRecord(PracticeResult result) {
        repository.insert(result);
    }

    ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            PracticeService.PracticeBinder binder = (PracticeService.PracticeBinder) service;
            mService = binder.getService();
            mService.run(practiceType);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

}
