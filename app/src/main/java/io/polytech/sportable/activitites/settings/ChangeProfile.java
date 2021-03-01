package io.polytech.sportable.activitites.settings;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import io.polytech.sportable.R;

public class ChangeProfile extends AppCompatActivity {
    // ну тут типа меняем профиль, наверное, в душе не ебу

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_profile);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void changeProfile(View view) {
        // меняем имя
    }

    public void changeBody(View view) {
        // меняем возраст дурачка
    }

    public void changeAge(View view) {
        // меняем характеристики теля дурачка
    }


    public void saveAndQuit(View view) {
        // сохраняем изменения и идем бегать
    }
}