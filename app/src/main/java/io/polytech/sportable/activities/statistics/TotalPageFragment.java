package io.polytech.sportable.activities.statistics;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import io.polytech.sportable.R;
import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.persistence.TotalFragmentInformation;

public class TotalPageFragment extends Fragment {

    TotalFragmentInformation inf;

    public TotalPageFragment(StatViewModel local) {
        this.inf = local.createTotalFragmentInformation();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Переписать в соответствии с нужно еденицей измерения
        View view = inflater.inflate(R.layout.total_fragment, container, false);
        TextView totalCalories = view.findViewById(R.id.total_all_calories);
        totalCalories.setText(inf.getTotalCalories() + " калорий");
        TextView averagedCalories = view.findViewById(R.id.total_average_calories);
        averagedCalories.setText(inf.getAveragedCalories() + "калорий");
        TextView maxCalories = view.findViewById(R.id.total_biggest_calories);
        maxCalories.setText(inf.getMaxCalories() + " калорий");
        TextView minCalories = view.findViewById(R.id.total_smallest_calories);
        minCalories.setText(inf.getMinCalories() + " калорий");
        TextView totalDistance = view.findViewById(R.id.total_all_distance);
        totalDistance.setText(inf.getTotalDistance() + " м.");
        TextView averagedDistance = view.findViewById(R.id.total_average_distance);
        averagedDistance.setText(inf.getAveragedDistance() + "м.");
        TextView maxDistance = view.findViewById(R.id.total_biggest_distance);
        maxDistance.setText(inf.getMaxDistance() + " м.");
        TextView minDistance = view.findViewById(R.id.total_smallest_distance);
        minDistance.setText(inf.getMinDistance() + " м.");
        TextView totalTime = view.findViewById(R.id.total_all_time);
        totalTime.setText(inf.getTotalTime());
        TextView averagedTime = view.findViewById(R.id.total_average_time);
        averagedTime.setText(inf.getAveragedTime());
        TextView maxTime = view.findViewById(R.id.total_biggest_time);
        maxTime.setText(inf.getMaxTime());
        TextView minTime = view.findViewById(R.id.total_smallest_time);
        minTime.setText(inf.getMinTime());
        TextView favouriteType = view.findViewById(R.id.total_favourite_workout);
        PracticeType type = inf.getFavouriteType();
        switch (type) {
            case Run:
                favouriteType.setText("Бег");
                break;
            case Walk:
                favouriteType.setText("Ходьба");
                break;
            case Skies:
                favouriteType.setText("Лыжи");
                break;
            case Bicycle:
                favouriteType.setText("Велорсипед");
                break;
        }
        return view;
    }
}