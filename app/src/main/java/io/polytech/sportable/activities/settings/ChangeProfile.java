package io.polytech.sportable.activities.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import io.polytech.sportable.R;

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

    // Меняем имя
    public void changeName(View view) {
        //UserData.setName(newName);
        Toast.makeText(this, "Пока недоступно :(", Toast.LENGTH_SHORT).show();
    }

    // Меняем $eк$
    public void changeSex(View view) {
        Toast.makeText(this, "Пока недоступно :(", Toast.LENGTH_SHORT).show();
    }

    // Меняем рост
    public void changeHeight(View view) {
        //UserData.setName(newName);
        Toast.makeText(this, "Пока недоступно :(", Toast.LENGTH_SHORT).show();
    }

    // Меняем вес
    public void changeWeight(View view) {
        //UserData.setName(newName);
        Toast.makeText(this, "Пока недоступно :(", Toast.LENGTH_SHORT).show();
    }

    // Меняем возраст
    public void changeAge(View view) {
        Toast.makeText(this, "Пока недоступно :(", Toast.LENGTH_SHORT).show();
    }

    public void saveAndQuit(View view) {
        Toast.makeText(this, "Вы выходите из настроек профиля!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ChangeProfile.this, SettingsActivity.class);
        startActivity(intent);
    }
}