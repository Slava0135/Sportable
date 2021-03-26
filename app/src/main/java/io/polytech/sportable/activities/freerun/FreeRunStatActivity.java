package io.polytech.sportable.activities.freerun;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.MainActivity;
import io.polytech.sportable.activities.statistics.StatActivity;

public class FreeRunStatActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_run_stat);

        Bundle arguments = getIntent().getExtras();

        float distance = (float) arguments.get("distance");
        int seconds = (int) arguments.get("time");
        int minutes = seconds / 60;
        float calories = (float) arguments.get("calories");
        float speed = (float) arguments.get("speed");

        final TextView textView = findViewById(R.id.textForUser);

        @SuppressLint("DefaultLocale")
        String str = String.format("Вы пробежали %.1f метров за %s:%s и сожгли %s калорий со средней скоростью %.2f км/ч",
                distance, new DecimalFormat( "00" ).format(minutes),  new DecimalFormat( "00" ).format(seconds),calories,speed);

        textView.setText(str);

        final Button buttonToMain = findViewById(R.id.buttonToMain);
        buttonToMain.setOnClickListener(v -> {
            Intent main = new Intent(FreeRunStatActivity.this, MainActivity.class);
            finish();
            startActivity(main);
        });

        final Button buttonStats = findViewById(R.id.buttonToStatistics);
        buttonStats.setOnClickListener(v -> {
            Intent stats = new Intent(FreeRunStatActivity.this, StatActivity.class);
            finish();
            startActivity(stats);
        });
    }
}