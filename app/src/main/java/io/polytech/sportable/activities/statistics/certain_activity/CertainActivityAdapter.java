package io.polytech.sportable.activities.statistics.certain_activity;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.statistics.ResultFormatter;
import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.persistence.PracticeResult;


public class CertainActivityAdapter {

    private final View view;

    public CertainActivityAdapter(View view) {
        this.view = view;
    }

    public void setData(List<PracticeResult> data) {
        List<PracticeResult> currentData = new ArrayList<>(data);
        if (currentData.isEmpty()) {
            currentData.add(
                    new PracticeResult(
                            0,
                            0.f,
                            0.f,
                            0,
                            PracticeType.Nothing
                    )
            );
        }
        float totalCalories = 0.f;
        float maxCalories = 0.f;
        float minCalories = Float.MAX_VALUE;
        float totalDistance = 0.f;
        float maxDistance = 0.f;
        float minDistance = Float.MAX_VALUE;
        int totalTime = 0;
        int maxTime = 0;
        int minTime = Integer.MAX_VALUE;
        for (PracticeResult practice: currentData) {
            float calories = practice.calories;
            float distance = practice.distance;
            int time = practice.time;
            totalCalories += calories;
            if (calories > maxCalories) maxCalories = calories;
            if (calories < minCalories) minCalories = calories;
            totalDistance += distance;
            if (distance > maxDistance) maxDistance = distance;
            if (distance < minDistance) minDistance = distance;
            totalTime += time;
            if (time > maxTime) maxTime = time;
            if (time < minTime) minTime = time;
        }
        float averagedCalories = totalCalories / currentData.size();
        float averagedDistance = totalDistance / currentData.size();
        int averagedTime = totalTime / currentData.size();

        TextView totalCaloriesTxt = view.findViewById(R.id.certain_activity_all_calories);
        TextView averagedCaloriesTxt = view.findViewById(R.id.certain_activity_average_calories);
        TextView maxCaloriesTxt = view.findViewById(R.id.certain_activity_biggest_calories);
        TextView minCaloriesTxt = view.findViewById(R.id.certain_activity_smallest_calories);
        TextView totalDistanceTxt = view.findViewById(R.id.certain_activity_all_distance);
        TextView averagedDistanceTxt = view.findViewById(R.id.certain_activity_average_distance);
        TextView maxDistanceTxt = view.findViewById(R.id.certain_activity_biggest_distance);
        TextView minDistanceTxt = view.findViewById(R.id.certain_activity_smallest_distance);
        TextView totalTimeTxt = view.findViewById(R.id.certain_activity_all_time);
        TextView averagedTimeTxt = view.findViewById(R.id.certain_activity_average_time);
        TextView maxTimeTxt = view.findViewById(R.id.certain_activity_biggest_time);
        TextView minTimeTxt = view.findViewById(R.id.certain_activity_smallest_time);

        ResultFormatter formatter = new ResultFormatter();

        totalCaloriesTxt.setText(formatter.formatCalories(totalCalories));
        averagedCaloriesTxt.setText(formatter.formatCalories(averagedCalories));
        maxCaloriesTxt.setText(formatter.formatCalories(maxCalories));
        minCaloriesTxt.setText(formatter.formatCalories(minCalories));
        totalDistanceTxt.setText(formatter.formatDistance(totalDistance));
        averagedDistanceTxt.setText(formatter.formatDistance(averagedDistance));
        maxDistanceTxt.setText(formatter.formatDistance(maxDistance));
        minDistanceTxt.setText(formatter.formatDistance(minDistance));
        totalTimeTxt.setText(formatter.formatTime(totalTime));
        averagedTimeTxt.setText(formatter.formatTime(averagedTime));
        maxTimeTxt.setText(formatter.formatTime(maxTime));
        minTimeTxt.setText(formatter.formatTime(minTime));
    }
}
