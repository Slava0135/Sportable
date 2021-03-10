package io.polytech.sportable.activities.freerun;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.MainActivity;
import io.polytech.sportable.activities.statistics.StatActivity;

public class FreeRunStatActivity extends AppCompatActivity {

    float distance;
    int time;
    int calories;
    float speed;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_run_stat);

        //кнопка назад
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle arguments = getIntent().getExtras();

        float distance = (float) arguments.get("distance");
        int minutes = (int) arguments.get("time") / 60;
        int seconds = (int) arguments.get("time") - minutes*60;
        int calories = (int) arguments.get("calories");
        float speed = (float) arguments.get("speed");

        final TextView textView = findViewById(R.id.textForUser);

        @SuppressLint("DefaultLocale")
        String str = String.format("Вы пробежали %.2f километров за %s:%s минут и сожгли %s калорий со средней скоростью %.2f км/ч",
                distance, new DecimalFormat( "00" ).format(minutes),  new DecimalFormat( "00" ).format(seconds),calories,speed);

        textView.setText(str);

        final Button buttonToMain = findViewById(R.id.buttonToMain);
        buttonToMain.setOnClickListener(v -> {
            Intent main = new Intent(FreeRunStatActivity.this, MainActivity.class);
            this.finish();
            startActivity(main);
        });

        final Button buttonStats = findViewById(R.id.buttonToStatistics);
        buttonStats.setOnClickListener(v -> {
            Intent stats = new Intent(FreeRunStatActivity.this, StatActivity.class);
            this.finish();
            startActivity(stats);
        });
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
}