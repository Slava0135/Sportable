package io.polytech.sportable.activities.statistics.certain_activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import io.polytech.sportable.R;
import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.persistence.PracticeResult;
import io.polytech.sportable.persistence.PracticeResultViewModel;

public class CertainActivityPageFragment extends Fragment {

    private PracticeResultViewModel mPracticeResultViewModel;

    public static CertainActivityPageFragment newInstance() {
        return new CertainActivityPageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.certain_activity_fragment, container, false);

        CertainActivityAdapter adapter = new CertainActivityAdapter(view);

        mPracticeResultViewModel = new ViewModelProvider(this).get(PracticeResultViewModel.class);

        TabLayout tabs = view.findViewById(R.id.certain_activity_tab_layout);
        mPracticeResultViewModel.getAllByPractice(PracticeType.Walk)
                .observe(getViewLifecycleOwner(), adapter::setData);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0: {
                        mPracticeResultViewModel.getAllByPractice(PracticeType.Walk)
                                .observe(getViewLifecycleOwner(), adapter::setData);
                        Toast.makeText(getContext(), "Вы выбрали Ходьбу", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 1: {
                        mPracticeResultViewModel.getAllByPractice(PracticeType.Run)
                                .observe(getViewLifecycleOwner(), adapter::setData);
                        Toast.makeText(getContext(), "Вы выбрали Бег", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 2:
                        mPracticeResultViewModel.getAllByPractice(PracticeType.Bicycle)
                                .observe(getViewLifecycleOwner(), adapter::setData);
                        Toast.makeText(getContext(), "Вы выбрали Велосипед", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        mPracticeResultViewModel.getAllByPractice(PracticeType.Skies)
                                .observe(getViewLifecycleOwner(), adapter::setData);
                        Toast.makeText(getContext(), "Вы выбрали Лыжи", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
}