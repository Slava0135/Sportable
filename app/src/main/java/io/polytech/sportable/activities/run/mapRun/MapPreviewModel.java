package io.polytech.sportable.activities.run.mapRun;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.geometry.SubpolylineHelper;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.transport.masstransit.PedestrianRouter;
import com.yandex.mapkit.transport.masstransit.Route;
import com.yandex.mapkit.transport.masstransit.Section;
import com.yandex.mapkit.transport.masstransit.Session;
import com.yandex.runtime.Error;

import java.util.List;
import java.util.Random;

public class MapPreviewModel extends AndroidViewModel implements Session.RouteListener {

    Point startLocation;
    MapView mapView;
    MapObjectCollection mapObjects;

    private float distanceInDegrees;

    PedestrianRouter router;

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

    @Override
    public void onMasstransitRoutes(@NonNull List<Route> list) {
        if (list.size() > 0) {
            for (Section section : list.get(0).getSections()) {
                mapObjects.addPolyline(SubpolylineHelper.subpolyline(list.get(0).getGeometry(), section.getGeometry()));
            }
        }
    }

    @Override
    public void onMasstransitRoutesError(@NonNull Error error) {

    }
}
