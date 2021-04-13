package io.polytech.sportable.activities.run.mapRun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;

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

        model.mapView = findViewById(R.id.mapPreview);
        model.mapView.getMap().move(
                new CameraPosition(model.startLocation, 17, 0, 0)
        );

        if (!model.isBuilt) {
            model.rebuild();
        }

        Button buttonStart = findViewById(R.id.startFromPreviewButton);
        buttonStart.setOnClickListener(v -> {
            Intent run = new Intent(MapPreviewActivity.this, MapRunActivity.class);
            startActivity(run);
            finish();
        });

        Button buttonRebuild = findViewById(R.id.rebuildButton);
        buttonRebuild.setOnClickListener(v -> {
            model.rebuild();
        });
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