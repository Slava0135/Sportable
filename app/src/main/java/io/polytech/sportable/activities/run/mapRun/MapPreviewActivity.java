package io.polytech.sportable.activities.run.mapRun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;

import io.polytech.sportable.R;

public class MapPreviewActivity extends AppCompatActivity {

    MapPreviewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_map_preview);
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MapPreviewModel.class);

        Bundle arguments = getIntent().getExtras();

        model.startLocation = new Point((double) arguments.get("latitude"), (double) arguments.get("longitude"));
        model.distance = (float) arguments.get("distance");

        model.mapView = findViewById(R.id.MapPreview);
        model.mapView.getMap().move(
                new CameraPosition(model.startLocation, 17, 0, 0)
        );
    }

    @Override
    protected void onStop() {
        model.mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        model.mapView.onStart();
        MapKitFactory.getInstance().onStart();
    }
}