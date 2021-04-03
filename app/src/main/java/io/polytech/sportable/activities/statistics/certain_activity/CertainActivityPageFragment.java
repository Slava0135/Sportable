package io.polytech.sportable.activities.statistics.certain_activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
        tabs.setOnClickListener(v -> {
            switch (tabs.getSelectedTabPosition()) {
                case 0:
                    mPracticeResultViewModel.getAllByPractice(PracticeType.Walk)
                            .observe(getViewLifecycleOwner(), adapter::setData);
                case 1:
                    mPracticeResultViewModel.getAllByPractice(PracticeType.Run)
                            .observe(getViewLifecycleOwner(), adapter::setData);
                case 2:
                    mPracticeResultViewModel.getAllByPractice(PracticeType.Bicycle)
                            .observe(getViewLifecycleOwner(), adapter::setData);
                case 3:
                    mPracticeResultViewModel.getAllByPractice(PracticeType.Skies)
                            .observe(getViewLifecycleOwner(), adapter::setData);
            }
        });
        return view;
    }
}