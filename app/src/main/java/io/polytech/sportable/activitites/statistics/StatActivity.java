package io.polytech.sportable.activitites.statistics;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import io.polytech.sportable.R;

public class StatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        ViewPager2 viewPager = findViewById(R.id.stat_view);
        viewPager.setAdapter(
                new StatActivityPagerAdapter(getSupportFragmentManager(), getLifecycle()));
    }
}