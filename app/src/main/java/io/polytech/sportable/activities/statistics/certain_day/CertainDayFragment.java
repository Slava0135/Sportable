package io.polytech.sportable.activities.statistics.certain_day;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Calendar;

import io.polytech.sportable.R;
import io.polytech.sportable.persistence.PracticeResultViewModel;

public class CertainDayFragment extends Fragment {

    private PracticeResultViewModel mPracticeResultViewModel;

    public static CertainDayFragment newInstance() {
        return new CertainDayFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.certain_day_fragment, container, false);

        CertainDayAdapter adapter = new CertainDayAdapter(view);

        mPracticeResultViewModel = new ViewModelProvider(this).get(PracticeResultViewModel.class);

        CalendarView calendarView = view.findViewById(R.id.calendarView);
        calendarView.setDate(Calendar.getInstance().getTimeInMillis(), true, true);
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            long date = Long.parseLong(String.valueOf(dayOfMonth) + month + year); //maybe a bug
            mPracticeResultViewModel.getByDate(date).observe(getViewLifecycleOwner(), adapter::setData);
        });

        return view;
    }
}