package io.polytech.sportable.activities.settings;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import io.polytech.sportable.R;
import io.polytech.sportable.persistence.UserData;

public class ChangeProfile extends AppCompatActivity {

    Button name_button;
    Button sex_button;
    Button height_button;
    Button weight_button;
    Button age_button;
    Button kill_button;

    final Context context = this;
    private TextView final_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_profile);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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
                //Editable value = input.getText();
                newInfo  = input.getText().toString();
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
        String newName = ch("МЕНЯЕМ ИМЯ", "имя любимое моё твоё именно");
        //Toast.makeText(this, newName, Toast.LENGTH_SHORT).show();

        UserData.setName(newName);
        Toast.makeText(this,
                newName + " ?= " + UserData.getName(), Toast.LENGTH_SHORT).show();
    }

    // Меняем пол
    public void changeSex(View view) {
        //Toast.makeText(this, "Пока недоступно :(", Toast.LENGTH_SHORT).show();

        float newAge = Float.parseFloat(ch("Введите новый возраст", ""));
        Toast.makeText(this, (int) newAge, Toast.LENGTH_SHORT).show();

        UserData.setYear(newAge);
    }

    // Меняем рост
    public void changeHeight(View view) {
        //UserData.setName(newName);
        //Toast.makeText(this, "Пока недоступно :(", Toast.LENGTH_SHORT).show();

        float newHeight = Float.parseFloat(ch("Введите новый рост", ""));
        Toast.makeText(this, (int) newHeight, Toast.LENGTH_SHORT).show();

        UserData.setYear(newHeight);
    }

    // Меняем вес
    public void changeWeight(View view) {
        //UserData.setName(newName);
        //Toast.makeText(this, "Пока недоступно :(", Toast.LENGTH_SHORT).show();

        float newWeight = Float.parseFloat(ch("Введите новый вес", ""));
        Toast.makeText(this, (int) newWeight, Toast.LENGTH_SHORT).show();

        UserData.setYear(newWeight);
    }

    // Меняем возраст
    public void changeAge(View view) {
        //Toast.makeText(this, "Пока недоступно :(", Toast.LENGTH_SHORT).show();

        float newAge = Float.parseFloat(ch("Введите новый возраст", ""));
        Toast.makeText(this, (int) newAge, Toast.LENGTH_SHORT).show();

        UserData.setYear(newAge);
    }

    public void saveAndQuit(View view) {
        Toast.makeText(this, "Вы выходите из настроек профиля!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ChangeProfile.this, SettingsActivity.class);
        startActivity(intent);
    }
}