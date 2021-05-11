package io.polytech.sportable.activities.run;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.MainActivity;

public class RunStatActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_stat);

        Bundle arguments = getIntent().getExtras();

        SharedPreferences settings = getSharedPreferences("io.polytech.sportable", MODE_PRIVATE);
        TextView nameView =  findViewById(R.id.textViewTop);
        String name = settings.getString("name", "[ДАННЫЕ УДАЛЕНЫ]");
        nameView.setText("Молодец, " + name + "!");

        float distance = (float) arguments.get("distance");
        int seconds = (int) arguments.get("time");
        int minutes = seconds / 60;
        float calories = (float) arguments.get("calories");
        float speed = (float) arguments.get("speed");

        final TextView textView = findViewById(R.id.textForUser);

        @SuppressLint("DefaultLocale")
        String str = String.format("Вы пробежали:\n%.1f метров\n\n\nЗа время:\n%s:%s\n\n\nСо средней скоростью:\n%.2f м/с \n\n\nСожгли:\n%s ккал",
                distance, new DecimalFormat( "00" ).format(minutes),  new DecimalFormat( "00" ).format(seconds),speed,calories);

        textView.setText(str);

        final Button buttonToMain = findViewById(R.id.buttonToMain);
        buttonToMain.setOnClickListener(v -> {
            Intent main = new Intent(RunStatActivity.this, MainActivity.class);
            finish();
            startActivity(main);
        });
    }
}