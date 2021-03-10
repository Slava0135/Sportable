package io.polytech.sportable.activities.freerun;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Random;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.freerun.FreeRunStatActivity;

public class FreeRunActivity extends AppCompatActivity {
    float distance = 0.0f;
    int seconds = 0;
    int calories = 0;
    float speed = 0;
    boolean isRunning;

    @SuppressLint({"SetTextI18n", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_run);
        //кнопка назад
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        isRunning = true;
        final Button buttonPause = findViewById(R.id.buttonPause);
        buttonPause.setOnClickListener(v -> {
            if (isRunning) {
                buttonPause.setText("continue");
                onPause();
            } else {
                buttonPause.setText("pause");
                onResume();
            }
        });

        final Button buttonStop = findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(v -> onStop());
        runTimer();
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        isRunning = false;
        super.onPause();
    }

    @Override
    public void onResume() {
        isRunning = true;
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent stats = new Intent(FreeRunActivity.this, FreeRunStatActivity.class);
        if (isRunning) {
            stats.putExtra("distance", distance);
            stats.putExtra("time", seconds);
            stats.putExtra("calories", calories);
            stats.putExtra("speed", speed);
            isRunning = false;
            startActivity(stats);
            finish();
        }
    }

    float getDistance(){
        Random random = new Random();
        distance += (float)random.nextInt(10)/10;
        return distance;
    }

    float getSpeed(){
        speed = distance / ((float)seconds / 3600);
        return speed;
    }

    int getCalories(){
        Random random = new Random();
        calories += random.nextInt(10);
        return calories;
    }

    public void runTimer() {
        final TextView valueTime = (TextView)findViewById(R.id.valueTime);
        final TextView valueDistance = (TextView)findViewById(R.id.valueDistance);
        final TextView valueSpeed = (TextView)findViewById(R.id.valueSpeed);
        final TextView valueCalories = (TextView)findViewById(R.id.valueCalories);
        final Handler handler = new Handler();
        handler.post(new Runnable() {

            @Override
            public void run() {
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                if (isRunning) {
                    String time = String.format(Locale.getDefault(), "%02d:%02d", minutes, secs);
                    String distanceN = "" + getDistance();
                    String speed = "" + getSpeed();
                    String calories = "" + getCalories();

                    valueTime.setText(time);
                    valueDistance.setText(distanceN);
                    valueSpeed.setText(speed);
                    valueCalories.setText(calories);
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

}