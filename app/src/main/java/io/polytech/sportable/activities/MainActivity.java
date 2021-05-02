package io.polytech.sportable.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.RadioGroup;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.run.mapRun.MapActivity;
import io.polytech.sportable.activities.settings.FirstEntry;
import io.polytech.sportable.activities.statistics.StatActivity;
import io.polytech.sportable.activities.run.freerun.FreeRunActivity;
import io.polytech.sportable.activities.settings.SettingsActivity;
import io.polytech.sportable.models.practice.PracticeType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.stats_button).setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), StatActivity.class);
            startActivity(intent);
        });

        prefs = getSharedPreferences("io.polytech.sportable", MODE_PRIVATE);
    }

    SharedPreferences prefs = null;

    @Override
    protected void onResume() {
        super.onResume();
        if (prefs.getBoolean("firstrun", true)) {
            // При первом запуске (или если юзер удалял все данные приложения) мы попадаем сюда.
            // Делаем что-то и после действия записывам false в переменную firstrun.
            // Итого при следующих запусках этот код не вызывается.
            Intent intent = new Intent(MainActivity.this, FirstEntry.class);
            startActivity(intent);
            prefs.edit().putBoolean("firstrun", false).apply();
        }
    }

    String typeMove = "";

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
                if (!isGeoEnabled()) {
                    enableGeo();
                } else {
                    Intent map = new Intent(MainActivity.this, MapActivity.class);
                    map.putExtra("activity_type", typeMove);
                    startActivity(map);
                }
                break;

            case R.id.freerun_button:
                if (!isGeoEnabled()) {
                    enableGeo();
                } else {
                    Intent freerun = new Intent(MainActivity.this, FreeRunActivity.class);
                    freerun.putExtra("activity_type", typeMove);
                    startActivity(freerun);
                    finish();
                }
                break;
            case R.id.runningMan:
                typeMove = PracticeType.Walk.toString();
                view.setBackgroundResource(R.drawable.ic_running_pressed);
                findViewById(R.id.walkingMan).setBackgroundResource(R.drawable.ic_walking);
                findViewById(R.id.cyclingMan).setBackgroundResource(R.drawable.ic_cycling);
                break;
            case R.id.walkingMan:
                typeMove = PracticeType.Run.toString();
                view.setBackgroundResource(R.drawable.ic_walking_pressed);
                findViewById(R.id.runningMan).setBackgroundResource(R.drawable.ic_running);
                findViewById(R.id.cyclingMan).setBackgroundResource(R.drawable.ic_cycling);
                break;
            case R.id.cyclingMan:
                typeMove = PracticeType.Bicycle.toString();
                view.setBackgroundResource(R.drawable.ic_cycling_pressed);
                findViewById(R.id.walkingMan).setBackgroundResource(R.drawable.ic_walking);
                findViewById(R.id.runningMan).setBackgroundResource(R.drawable.ic_running);
                break;
        }
    }

    public boolean isGeoEnabled() {
        Context mContext = getApplicationContext();
        LocationManager mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        boolean mIsGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean mIsNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return mIsGPSEnabled || mIsNetworkEnabled;
    }

    public void enableGeo() {
        Toast.makeText(this, "Включите геолокацию, чтобы приложение работало корректно", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        this.finish();
    }
}