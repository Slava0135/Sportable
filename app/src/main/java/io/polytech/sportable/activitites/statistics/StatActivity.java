package io.polytech.sportable.activitites.statistics;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import io.polytech.sportable.R;
import io.polytech.sportable.activitites.MainActivity;

public class StatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(
                new Adapter(getSupportFragmentManager(), StatActivity.this)
        );
        TabLayout tabLayout = findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}