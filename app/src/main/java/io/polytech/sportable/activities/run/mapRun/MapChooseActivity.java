package io.polytech.sportable.activities.run.mapRun;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.GeoObjectTapEvent;
import com.yandex.mapkit.layers.GeoObjectTapListener;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.InputListener;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;

import io.polytech.sportable.R;

public class MapChooseActivity extends AppCompatActivity implements GeoObjectTapListener, InputListener {

    Point startLocation;
    MapView mapView;
    MapObjectCollection mapObjects;
    float distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey("18400120-4838-48c7-905a-b2abc915eab0");
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_map_choose);

        startLocation = new Point(savedInstanceState.getFloat("latitude"), savedInstanceState.getFloat("longitude"));
        distance = savedInstanceState.getFloat("distance");

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
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    @Override
    public void onMapTap(@NonNull Map map, @NonNull Point point) {
        mapObjects.clear();
        mapObjects.addPlacemark(startLocation);
        startLocation = point;
    }

    @Override
    public void onMapLongTap(@NonNull Map map, @NonNull Point point) {
    }

    @Override
    public boolean onObjectTap(@NonNull GeoObjectTapEvent geoObjectTapEvent) {
        return false;
    }
}