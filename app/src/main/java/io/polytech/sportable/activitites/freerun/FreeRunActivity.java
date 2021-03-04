package io.polytech.sportable.activitites.freerun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import io.polytech.sportable.R;

public class FreeRunActivity extends AppCompatActivity {
    float distance = 0.0f;
    int seconds = 0;
    int calories = 0;
    boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_run);
        isRunning = true;
        final Button buttonPause = findViewById(R.id.buttonPause);
        buttonPause.setOnClickListener(v -> {
            if (isRunning) {
                onPause();
            } else {
                onResume();
            }
        });

        final Button buttonStop = findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(v -> onStop());


        runTimer();
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
        isRunning = false;

    }

    public void runTimer() {
        final TextView valueTime = (TextView)findViewById(R.id.valueTime);
        final Handler handler = new Handler();
        handler.post(new Runnable() {

            @Override
            public void run() {
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(), "%02d:%02d", minutes, secs);

                valueTime.setText(time);

                if (isRunning) {
                    seconds++;
                }

                //delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }

}