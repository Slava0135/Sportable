package io.polytech.sportable.activities.statistics.total;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.polytech.sportable.R;
import io.polytech.sportable.persistence.PracticeResult;
import io.polytech.sportable.persistence.PracticeResultViewModel;

public class TotalPageFragment extends Fragment {

    private PracticeResultViewModel mPracticeResultViewModel;

    public static TotalPageFragment newInstance() {
        return new TotalPageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.total_fragment, container, false);

        TotalAdapter adapter = new TotalAdapter();

        mPracticeResultViewModel = new ViewModelProvider(this).get(PracticeResultViewModel.class);
        mPracticeResultViewModel.allData.observe(getViewLifecycleOwner(), adapter::setData);
        return view;
    }
}