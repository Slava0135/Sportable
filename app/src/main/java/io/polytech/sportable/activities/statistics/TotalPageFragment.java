package io.polytech.sportable.activities.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import io.polytech.sportable.R;
import io.polytech.sportable.persistence.PracticeRepository;

public class TotalPageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    public static TotalPageFragment newInstance() {
        return new TotalPageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.total_fragment, container, false);
    }
}