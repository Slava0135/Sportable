package io.polytech.sportable.activities.run.mapRun;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;

import java.util.Random;

public class MapPreviewModel extends AndroidViewModel {

    Point startLocation;
    MapView mapView;
    MapObjectCollection mapObjects;

    private float distanceInDegrees;

    Random random = new Random();

    public MapPreviewModel(@NonNull Application application) {
        super(application);
    }

    void rebuild() {
        mapObjects.clear();
        mapObjects.addPlacemark(startLocation);

        double x0 = startLocation.getLongitude();
        double y0 = startLocation.getLatitude();

        double pos = 2 * Math.PI * random.nextFloat();
        double x = distanceInDegrees * Math.cos(pos);
        double y = distanceInDegrees * Math.sin(pos);

        double xp = x / Math.cos(Math.toRadians(y0));

        Point endLocation = new Point(y0 + y, x0 + xp);
        mapObjects.addPlacemark(endLocation);
    }

    public void setDistance(float distance) {
        distanceInDegrees = distance / 111000f;
    }
}
