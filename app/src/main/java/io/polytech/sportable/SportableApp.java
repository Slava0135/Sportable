package io.polytech.sportable;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;

public class SportableApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey("18400120-4838-48c7-905a-b2abc915eab0");
    }
}
