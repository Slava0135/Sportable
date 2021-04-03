package io.polytech.sportable.activities.statistics.certain_day;

import android.view.View;
import android.widget.TextView;

import io.polytech.sportable.R;
import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.persistence.PracticeResult;

public class CertainDayAdapter {

    private View view;

    private PracticeResult data;

    private PracticeType type;
    private float calories;
    private float distance;
    private int time;

    private TextView certainDayTypeTxt, certainDayCaloriesTxt, certainDayDistanceTxt;
    private TextView certainDayTimeTxt;

    public CertainDayAdapter(View view) {
        this.view = view;
    }

    public void setData(PracticeResult data) {
        this.data = data;
        type = data.practiceType;
        calories = data.calories;
        distance = data.distance;
        time = data.time;

        certainDayTypeTxt = view.findViewById(R.id.certain_day_kind_of_workout);
        certainDayCaloriesTxt = view.findViewById(R.id.certain_day_all_calories);
        certainDayDistanceTxt = view.findViewById(R.id.certain_day_distance);
        certainDayTimeTxt = view.findViewById(R.id.certain_day_time);

        certainDayTypeTxt.setText(String.valueOf(type));
        certainDayCaloriesTxt.setText(String.valueOf(calories));
        certainDayTimeTxt.setText(String.valueOf(distance));
        certainDayTimeTxt.setText(String.valueOf(time));
    }
}
