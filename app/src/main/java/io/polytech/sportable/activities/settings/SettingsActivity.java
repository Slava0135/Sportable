package io.polytech.sportable.activities.settings;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import io.polytech.sportable.R;
import io.polytech.sportable.activities.MainActivity;
import io.polytech.sportable.persistence.PracticeRepository;

public class SettingsActivity extends AppCompatActivity {

    PracticeRepository deleteStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        deleteStat = new PracticeRepository(getApplication());
    }

    public void changeProfile(View view) {
        Toast.makeText(this, "Вы будете менять профиль!", Toast.LENGTH_SHORT).show();
        Intent intentChangeProfile =
                new Intent(SettingsActivity.this, ChangeProfile.class);
        startActivity(intentChangeProfile);
    }

    public void clearStatistics(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Удалить статистику");
        builder.setMessage("Вы уверены, что хотите всего этого?");

        builder.setPositiveButton("ДА", (dialog, which) -> {
            deleteStat.deleteAll();
            Toast.makeText(SettingsActivity.this,
                    "Работает или нет? who knows..", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        builder.setNegativeButton("НЕТ", (dialog, which) -> dialog.dismiss());

        AlertDialog alert = builder.create();
        alert.show();
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

    public void mainActivity(View view) {
        Intent intentMainActivity =
                new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intentMainActivity);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "Вы выходите из настроек!", Toast.LENGTH_SHORT).show();
            Intent intentQuit = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intentQuit);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}