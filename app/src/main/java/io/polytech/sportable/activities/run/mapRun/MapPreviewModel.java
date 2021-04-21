package io.polytech.sportable.activities.run.mapRun;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.yandex.mapkit.RequestPoint;
import com.yandex.mapkit.RequestPointType;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.geometry.SubpolylineHelper;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.transport.masstransit.MasstransitOptions;
import com.yandex.mapkit.transport.masstransit.PedestrianRouter;
import com.yandex.mapkit.transport.masstransit.Route;
import com.yandex.mapkit.transport.masstransit.Section;
import com.yandex.mapkit.transport.masstransit.Session;
import com.yandex.mapkit.transport.masstransit.TimeOptions;
import com.yandex.runtime.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.polytech.sportable.SportableApp;
import io.polytech.sportable.models.practice.PracticeType;

import static com.yandex.runtime.Runtime.getApplicationContext;

public class MapPreviewModel extends AndroidViewModel implements Session.RouteListener {

    Point startLocation;
    MapView mapView;
    MapObjectCollection mapObjects;

    PracticeType practiceType;

    private float distanceInDegrees;
    float distance;

    PedestrianRouter router;
    TimeOptions timeOptions = new TimeOptions();

    Random random = new Random();

    public MapPreviewModel(@NonNull Application application) {
        super(application);
    }

    void rebuild() {
        mapObjects.clear();

        double x0 = startLocation.getLongitude();
        double y0 = startLocation.getLatitude();

        double pos = 2 * Math.PI * random.nextFloat();
        double x = distanceInDegrees * Math.cos(pos);
        double y = distanceInDegrees * Math.sin(pos);

        double xp = x / Math.cos(Math.toRadians(y0));

        Point endLocation = new Point(y0 + y, x0 + xp);

        List<RequestPoint> points = new ArrayList<>();
        points.add(new RequestPoint(startLocation, RequestPointType.WAYPOINT, null));
        points.add(new RequestPoint(endLocation, RequestPointType.WAYPOINT, null));
        router.requestRoutes(points, timeOptions, this);
    }

    public void setDistance(float distance) {
        this.distance = distance;
        distanceInDegrees = distance / 111000f;
    }

    @Override
    public void onMasstransitRoutes(@NonNull List<Route> list) {
        if (list.size() > 0) {
            ((SportableApp) getApplication()).lastRoute = list.get(0);
            for (Section section : list.get(0).getSections()) {
                mapObjects.addPolyline(SubpolylineHelper.subpolyline(list.get(0).getGeometry(), section.getGeometry())).setStrokeColor(0xFF24a1a6);
            }
        }
    }

    @Override
    public void onMasstransitRoutesError(@NonNull Error error) {

    }
}
