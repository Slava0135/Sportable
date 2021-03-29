package io.polytech.sportable.activities.statistics.certain_day;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import io.polytech.sportable.R;

public class CertainDayFragment extends Fragment {

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
        return inflater.inflate(R.layout.certain_day_fragment, container, false);
    }
}