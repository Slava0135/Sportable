package io.polytech.sportable.activities.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.MainActivity;
import io.polytech.sportable.activities.statistics.certain_activity.CertainActivityPageFragment;
import io.polytech.sportable.activities.statistics.certain_day.CertainDayFragment;
import io.polytech.sportable.activities.statistics.total.TotalPageFragment;

public class StatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        ImageView backArrow = findViewById(R.id.stat_back);
        backArrow.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            finish();
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        ViewPager2 viewPager = findViewById(R.id.stat_view);
        TabLayout tabLayout = findViewById(R.id.tabs);
        viewPager.setAdapter(new FragmentStateAdapter(getSupportFragmentManager(), getLifecycle()) {

            final int PAGE_COUNT = 3;

            @Override
            public int getItemCount() {
                return PAGE_COUNT;
            }

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                    switch (position) {
                        case 0:
                            return TotalPageFragment.newInstance();
                        case 1:
                            return CertainActivityPageFragment.newInstance();
                        case 2:
                            return CertainDayFragment.newInstance();
                        default:
                            return null;
                    }
                }
        });
        TabLayoutMediator mediator = new TabLayoutMediator(
                tabLayout, viewPager, true, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Общее");
                    break;
                case 1:
                    tab.setText("По тренировкам");
                    break;
                case 2:
                    tab.setText("По дням");
                    break;
            }
            viewPager.setCurrentItem(tab.getPosition(), true);
        });
        mediator.attach();
    }
}