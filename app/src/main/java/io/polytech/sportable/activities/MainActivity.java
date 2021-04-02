package io.polytech.sportable.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.mapRun.MapActivity;
import io.polytech.sportable.activities.statistics.StatActivity;
import io.polytech.sportable.activities.freerun.FreeRunActivity;
import io.polytech.sportable.activities.settings.SettingsActivity;
//Удалить этот комментарий
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.stats_button).setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), StatActivity.class);
            startActivity(intent);
        });
    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onMyButtonClick(View view) {
        switch (view.getId()) {
            case R.id.stats_button:
                Intent stats = new Intent(MainActivity.this, StatActivity.class);
                startActivity(stats);
                break;

            case R.id.settings_button:
                Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settings);
                break;

            case R.id.map_button:
                Intent map = new Intent(MainActivity.this, MapActivity.class);
                if (!isGeoEnabled()) {
                    enableGeo();
                } else {
                    startActivity(map);
                    finish();
                }
                break;

            case R.id.freerun_button:
                if (!isGeoEnabled()) {
                    enableGeo();
                } else {
                    Intent freerun = new Intent(MainActivity.this, FreeRunActivity.class);
                    startActivity(freerun);
                    finish();
                }
                break;
        }
    }

    public boolean isGeoEnabled() {
        Context mContext = getApplicationContext();
        LocationManager mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        boolean mIsGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean mIsNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean mIsGeoDisabled = mIsGPSEnabled && mIsNetworkEnabled;
        return mIsGeoDisabled;
    }

    public void enableGeo() {
        Toast.makeText(this, "Еблан зачем ты зашел в карты без геолокации? Соси хуй еп)", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        this.finish();
    }
}