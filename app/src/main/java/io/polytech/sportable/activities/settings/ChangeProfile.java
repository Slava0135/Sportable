package io.polytech.sportable.activities.settings;

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
    private static final Float WEIGHT = null;
    private static final Float HEIGHT = null;
    private static final Integer YEAR = null;
    private static final String SEX = "M";

    Button name_button;
    Button sex_button;
    Button height_button;
    Button weight_button;
    Button age_button;
    Button kill_button;

    final Context context = this;
    private TextView final_text;

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_profile);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        settings = getSharedPreferences("io.polytech.sportable", MODE_PRIVATE);

        TextView nameView = (TextView) findViewById(R.id.nameView);
        String name = settings.getString(NAME,"не определено");
        nameView.setText(name);

        /*
        name_button = (Button) findViewById(R.id.name_button);
        sex_button = (Button) findViewById(R.id.sex_button);
        height_button = (Button) findViewById(R.id.height_button);
        weight_button = (Button) findViewById(R.id.weight_button);
        age_button = (Button) findViewById(R.id.age_button);
        kill_button = (Button) findViewById(R.id.kill_button);

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.name_button:
                       changeName(view);
                        break;
                    case R.id.sex_button:
                        changeSex(view);
                        break;
                    case R.id.height_button:
                        changeHeight(view);
                        break;
                    case R.id.weight_button:
                        changeWeight(view);
                        break;
                    case R.id.age_button:
                        changeAge(view);
                        break;
                    case R.id.kill_button:
                        saveAndQuit(view);
                        break;
                }

            }
        };

        name_button.setOnClickListener(onClickListener);
        sex_button.setOnClickListener(onClickListener);
        height_button.setOnClickListener(onClickListener);
        weight_button.setOnClickListener(onClickListener);
        age_button.setOnClickListener(onClickListener);
        kill_button.setOnClickListener(onClickListener);
         */
    }

    public String newInfo;

    public String ch (String title, String message) {

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

    public void getName(View view) {
        ((TextView) findViewById(R.id.nameView))
                .setText(settings.getString(NAME,""));
    }


    // Меняем пол
    public void changeSex(View view) {
        Toast.makeText(ChangeProfile.this, "недоступно", Toast.LENGTH_SHORT).show();
    }

    // Меняем рост
    public void changeHeight(View view) {

        /*String newHeight = ch("Введите новый рост",
                "Рост на данный момент: " + UserData.getHeight());

        UserData.setYear(Float.parseFloat(newHeight));*/
    }

    // Меняем вес
    public void changeWeight(View view) {
        Toast.makeText(ChangeProfile.this, "недоступно", Toast.LENGTH_SHORT).show();
        /*String newWeight = ch("Введите новый вес",
                "Вес на данный момент:" + UserData.getWeight());

        UserData.setYear(Float.parseFloat(newWeight));*/
    }

    // Меняем возраст
    public void changeAge(View view) {
        Toast.makeText(ChangeProfile.this, "недоступно", Toast.LENGTH_SHORT).show();
        /*String newYear = ch("Введите новый год рождения",
                "Год рождения на данный момент: " + UserData.getYear());

        UserData.setYear(Integer.parseInt(newYear));*/
    }

    public void saveAndQuit(View view) {
        Toast.makeText(this, "Вы выходите из настроек профиля!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ChangeProfile.this, SettingsActivity.class);
        startActivity(intent);
    }
}