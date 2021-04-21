package io.polytech.sportable;

import android.app.Application;
import android.content.Context;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.transport.masstransit.Route;
import com.yandex.mapkit.transport.masstransit.Section;

import java.util.List;

public class SportableApp extends Application {

    public List<Point> lastRoute;

    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey("18400120-4838-48c7-905a-b2abc915eab0");
    }
}
