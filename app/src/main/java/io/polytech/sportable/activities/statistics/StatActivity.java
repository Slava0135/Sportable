package io.polytech.sportable.activities.statistics;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import io.polytech.sportable.R;
import io.polytech.sportable.persistence.PracticeRepository;
import io.polytech.sportable.persistence.PracticeResult;

public class StatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        ViewPager2 viewPager = findViewById(R.id.stat_view);
        TabLayout tabLayout = findViewById(R.id.tabs);
        viewPager.setAdapter(
                new StatActivityPagerAdapter(getSupportFragmentManager(), getLifecycle()));
        TabLayoutMediator mediator = new TabLayoutMediator(
                tabLayout, viewPager, true, (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Total");
                            break;
                        case 1:
                            tab.setText("Certain Activity");
                            break;
                        case 2:
                            tab.setText("Certain Day");
                            break;
                    }
                    viewPager.setCurrentItem(tab.getPosition(), true);
        });
        mediator.attach();
        /*PracticeRepository local = new PracticeRepository(getApplication());
        List<PracticeResult> localData = local.getAllPractices().getValue();
        TextView totalAllCalories = findViewById(R.id.total_all_calories);
        TextView totalAveragedCalories = findViewById(R.id.total_averaged_calories_in_one_workout);
        TextView totalAllDistance = findViewById(R.id.total_all_distance);
        TextView totalAveragedDistance = findViewById(R.id.total_averaged_distance_in_one_workout);*/
    }
}