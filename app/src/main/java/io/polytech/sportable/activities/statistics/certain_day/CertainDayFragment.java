package io.polytech.sportable.activities.statistics.certain_day;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    @SuppressLint("SimpleDateFormat")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.certain_day_fragment, container, false);

        CertainDayAdapter adapter = new CertainDayAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.to_insert);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        mPracticeResultViewModel = new ViewModelProvider(this).get(PracticeResultViewModel.class);

        CalendarView calendarView = view.findViewById(R.id.calendarView);
        long currentDateInMillis = 0;
        calendarView.setDate(Calendar.getInstance().getTimeInMillis(), true, true);
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date today = new Date();
        try {
            Date todayWithZeroTime = formatter.parse(formatter.format(today));
            assert todayWithZeroTime != null;
            currentDateInMillis = todayWithZeroTime.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mPracticeResultViewModel.getByDate(currentDateInMillis)
                .observe(getViewLifecycleOwner(), adapter::setData);
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
            Date date = calendar.getTime();
            long dateInMillis = date.getTime();
            mPracticeResultViewModel.getByDate(dateInMillis)
                    .observe(getViewLifecycleOwner(), adapter::setData);
        });
        return view;
    }
}