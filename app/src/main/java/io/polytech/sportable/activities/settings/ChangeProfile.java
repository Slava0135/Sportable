package io.polytech.sportable.activities.settings;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import io.polytech.sportable.R;

public class ChangeProfile extends AppCompatActivity {

    public static final String NAME = "Name";
    private static final String HEIGHT = "0";
    private static final String WEIGHT = "0.0";
    private static final String YEAR = "0";
    private static final String SEX = "M";

    final Context context = this;
    private TextView final_text;

    SharedPreferences settings;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_profile);


        settings = getSharedPreferences("io.polytech.sportable", MODE_PRIVATE);

        TextView nameView = (TextView) findViewById(R.id.nameView);
        String name = settings.getString(NAME,"user");
        nameView.setText(name);

        TextView heightView = (TextView) findViewById(R.id.heightView);
        int height = settings.getInt(HEIGHT,0);
        heightView.setText(Float.toString(height));

        TextView weightView = (TextView) findViewById(R.id.weightView);
        float weight = settings.getFloat(WEIGHT,0);
        weightView.setText(Float.toString(weight));

        TextView yearView = (TextView) findViewById(R.id.yearView);
        int year = settings.getInt(YEAR,0);
        yearView.setText(year);
/**/
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public String newInfo;

    public String ch(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title);
        builder.setMessage(message);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                newInfo  = input.getText().toString();
                Toast.makeText(ChangeProfile.this,
                        "Изменения сохранены :)", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(ChangeProfile.this,
                        "Изменения не были внесены :(", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        builder.show();

        return newInfo;
    }


    // Меняем имя
    public void changeName(View view) {
        // получаем введенное имя
        EditText nameBox = (EditText) findViewById(R.id.nameBox);
        String name = nameBox.getText().toString();
        // сохраняем его в настройках
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putString(NAME, name);
        prefEditor.apply();

        TextView nameView = (TextView) findViewById(R.id.nameView);
        nameView.setText(name);
    }
/*
    // Меняем пол
    public void changeSex(View view) {
        Toast.makeText(ChangeProfile.this, "недоступно", Toast.LENGTH_SHORT).show();
    }

*/

    // Меняем рост
    @SuppressLint("SetTextI18n")
    public void changeHeight(View view) {

        EditText heightBox = (EditText) findViewById(R.id.heightBox);
        int height = Integer.parseInt(heightBox.getText().toString());
        // сохраняем его в настройках
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putInt(HEIGHT, height);
        prefEditor.apply();

        TextView heightView = (TextView) findViewById(R.id.heightView);
        heightView.setText(Integer.toString(height));
    }

    // Меняем вес
    @SuppressLint("SetTextI18n")
    public void changeWeight(View view) {
        EditText weightBox = (EditText) findViewById(R.id.weightBox);
        float weight = Float.parseFloat(weightBox.getText().toString());
        // сохраняем его в настройках
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putFloat(WEIGHT, weight);
        prefEditor.apply();

        TextView weightView = (TextView) findViewById(R.id.weightView);
        weightView.setText(Float.toString(weight));
    }

    // Меняем возраст
    @SuppressLint("SetTextI18n")
    public void changeYear(View view) {
        EditText yearBox = (EditText) findViewById(R.id.yearBox);
        int year = Integer.parseInt(yearBox.getText().toString());
        // сохраняем его в настройках
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putInt(YEAR, year);
        prefEditor.apply();

        TextView yearView = (TextView) findViewById(R.id.yearView);
        yearView.setText(year);
    }

    public void saveAndQuit(View view) {
        Toast.makeText(this, "Вы выходите из настроек профиля!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ChangeProfile.this, SettingsActivity.class);
        startActivity(intent);
    }
}