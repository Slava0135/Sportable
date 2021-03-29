package io.polytech.sportable.activities.statistics.certain_activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import io.polytech.sportable.R;

public class CertainActivityPageFragment extends Fragment {

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
        return inflater.inflate(R.layout.certain_activity_fragment, container, false);
    }
}