package io.polytech.sportable.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticeCollector extends Service {
    public PracticeCollector() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}