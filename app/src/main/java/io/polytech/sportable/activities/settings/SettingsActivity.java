package io.polytech.sportable.activities.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.MainActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void changeProfile(View view) {
        Toast.makeText(this, "Вы будете менять профиль!", Toast.LENGTH_SHORT).show();
        Intent intentChangeProfile =
                new Intent(SettingsActivity.this, ChangeProfile.class);
        startActivity(intentChangeProfile);
    }

    public void clearStatistics(View view) {
        // удаляем всю статистику
        Toast.makeText(this, "Пока недоступно :(", Toast.LENGTH_SHORT).show();
    }

    public void saveAndQuit(View view) {
        Toast.makeText(this, "Вы выходите из настроек!", Toast.LENGTH_SHORT).show();
        Intent intentQuit = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intentQuit);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}