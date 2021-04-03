package io.polytech.sportable.activities.statistics.certain_activity;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.polytech.sportable.R;
import io.polytech.sportable.persistence.PracticeResult;

public class CertainActivityAdapter {

    private View view;

    private List<PracticeResult> data = new ArrayList<>();

    private float totalCalories;
    private float averagedCalories;
    private float maxCalories;
    private float minCalories;
    private float totalDistance;
    private float averagedDistance;
    private float maxDistance;
    private float minDistance;
    private int totalTime;
    private int averagedTime;
    private int maxTime;
    private int minTime;

    private TextView totalCaloriesTxt, averagedCaloriesTxt, maxCaloriesTxt, minCaloriesTxt;
    private TextView totalDistanceTxt, averagedDistanceTxt, maxDistanceTxt, minDistanceTxt;
    private TextView totalTimeTxt, averagedTimeTxt, maxTimeTxt, minTimeTxt;

    public CertainActivityAdapter(View view) {
        this.view = view;
    }

    public void setData(List<PracticeResult> data) {
        this.data = data;
        totalCalories = 0.f;
        maxCalories = 0.f;
        minCalories = Float.MAX_VALUE;
        totalDistance = 0.f;
        maxDistance = 0.f;
        minDistance = Float.MAX_VALUE;
        totalTime = 0;
        maxTime = 0;
        minTime = Integer.MAX_VALUE;
        for (PracticeResult practice: data) {
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
        averagedCalories = totalCalories / data.size();
        averagedDistance = totalDistance / data.size();
        averagedTime = totalTime / data.size();

        totalCaloriesTxt = view.findViewById(R.id.certain_activity_all_calories);
        averagedCaloriesTxt = view.findViewById(R.id.certain_activity_average_calories);
        maxCaloriesTxt = view.findViewById(R.id.certain_activity_biggest_calories);
        minCaloriesTxt = view.findViewById(R.id.certain_activity_smallest_calories);
        totalDistanceTxt = view.findViewById(R.id.certain_activity_all_distance);
        averagedDistanceTxt = view.findViewById(R.id.certain_activity_average_distance);
        maxDistanceTxt = view.findViewById(R.id.certain_activity_biggest_distance);
        minDistanceTxt = view.findViewById(R.id.certain_activity_smallest_distance);
        totalTimeTxt = view.findViewById(R.id.certain_activity_all_time);
        averagedTimeTxt = view.findViewById(R.id.certain_activity_average_time);
        maxTimeTxt = view.findViewById(R.id.certain_activity_biggest_time);
        minTimeTxt = view.findViewById(R.id.certain_activity_smallest_time);

        totalCaloriesTxt.setText(String.valueOf(totalCalories));
        averagedCaloriesTxt.setText(String.valueOf(averagedCalories));
        maxCaloriesTxt.setText(String.valueOf(maxCalories));
        minCaloriesTxt.setText(String.valueOf(minCalories));
        totalDistanceTxt.setText(String.valueOf(totalDistance));
        averagedDistanceTxt.setText(String.valueOf(averagedDistance));
        maxDistanceTxt.setText(String.valueOf(maxDistance));
        minDistanceTxt.setText(String.valueOf(minDistance));
        totalTimeTxt.setText(String.valueOf(totalTime));
        averagedTimeTxt.setText(String.valueOf(averagedTime));
        maxTimeTxt.setText(String.valueOf(maxTime));
        minTimeTxt.setText(String.valueOf(minTime));
    }
}
