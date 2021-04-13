package io.polytech.sportable.activities.run.mapRun;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;

public class MapPreviewModel extends AndroidViewModel {


    Point startLocation;
    MapView mapView;
    MapObjectCollection mapObjects;
    float distance;

    public MapPreviewModel(@NonNull Application application) {
        super(application);
    }
}
