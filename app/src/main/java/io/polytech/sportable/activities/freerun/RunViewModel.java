package io.polytech.sportable.activities.freerun;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import androidx.lifecycle.ViewModel;

import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.services.PracticeService;

public class RunViewModel extends ViewModel {

    PracticeType practiceType = PracticeType.Run;
    boolean isRunning;
    PracticeService mService;
    boolean mBound = false;

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
