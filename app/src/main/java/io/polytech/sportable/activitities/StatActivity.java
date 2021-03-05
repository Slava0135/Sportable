package io.polytech.sportable.activitities;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import io.polytech.sportable.R;

public class StatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        ViewPager viewPager = findViewById(R.id.view_pager);
    }
}