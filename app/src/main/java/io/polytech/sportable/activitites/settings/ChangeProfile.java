package io.polytech.sportable.activitites.settings;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import io.polytech.sportable.R;
import io.polytech.sportable.persistence.UserData;

public class ChangeProfile extends AppCompatActivity {

    Button name_button;
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
        height_button.setOnClickListener(onClickListener);
        weight_button.setOnClickListener(onClickListener);
        age_button.setOnClickListener(onClickListener);
        kill_button.setOnClickListener(onClickListener);


    }

    // Меняем имя
    public void changeName(View view) {

        //UserData.setName(newName);

    }

    // Меняем рост
    public void changeHeight(View view) {
        //UserData.setName(newName);
    }

    // Меняем вес
    public void changeWeight(View view) {
        //UserData.setName(newName);
    }

    // Меняем возраст
    public void changeAge(View view) {

    }

    public void saveAndQuit(View view) {
        // сохраняем изменения и идем бегать
    }
}