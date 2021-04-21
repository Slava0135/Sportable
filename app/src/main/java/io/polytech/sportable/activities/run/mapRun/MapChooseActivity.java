package io.polytech.sportable.activities.run.mapRun;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.directions.DirectionsFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.GeoObjectTapEvent;
import com.yandex.mapkit.layers.GeoObjectTapListener;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.InputListener;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;

import io.polytech.sportable.R;
import io.polytech.sportable.models.practice.PracticeType;

public class MapChooseActivity extends AppCompatActivity implements GeoObjectTapListener, InputListener {

    Point startLocation;
    MapView mapView;
    MapObjectCollection mapObjects;

    PracticeType practiceType;

    float distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_map_choose);

        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();

        practiceType = PracticeType.valueOf((String) arguments.get("activity_type"));
        startLocation = new Point((double) arguments.get("latitude"), (double) arguments.get("longitude"));
        distance = (float) arguments.get("distance");

        mapView = findViewById(R.id.chooseMapview);
        mapView.getMap().addTapListener(this);
        mapView.getMap().addInputListener(this);
        mapView.getMap().move(
                new CameraPosition(startLocation, 17, 0, 0)
        );
        mapObjects = mapView.getMap().getMapObjects().addCollection();

        Button buttonStart = findViewById(R.id.chooseStartButton);
        buttonStart.setOnClickListener(v -> {
            Intent preview = new Intent(MapChooseActivity.this, MapPreviewActivity.class);
            preview.putExtra("distance", distance);
            preview.putExtra("latitude", startLocation.getLatitude());
            preview.putExtra("longitude", startLocation.getLongitude());
            preview.putExtra("activity_type", practiceType.toString());
            startActivity(preview);
            finish();
        });
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
        MapKitFactory.getInstance().onStart();
    }

    @Override
    public void onMapTap(@NonNull Map map, @NonNull Point point) {
        mapObjects.clear();
        startLocation = point;
        mapObjects.addPlacemark(startLocation);
    }

    @Override
    public void onMapLongTap(@NonNull Map map, @NonNull Point point) {
    }

    @Override
    public boolean onObjectTap(@NonNull GeoObjectTapEvent geoObjectTapEvent) {
        return false;
    }
}