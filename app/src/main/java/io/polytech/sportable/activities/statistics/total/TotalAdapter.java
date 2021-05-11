package io.polytech.sportable.activities.statistics.total;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.polytech.sportable.R;
import io.polytech.sportable.activities.statistics.ResultFormatter;
import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.persistence.PracticeResult;

public class TotalAdapter {

    private final View view;

    public TotalAdapter(View view) {
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
        PracticeType favouriteType = null;
        Map<PracticeType, Integer> compareMap = new HashMap<>();
        for (PracticeResult practice: currentData) {
            float calories = practice.calories;
            float distance = practice.distance;
            int time = practice.time;
            PracticeType type = practice.practiceType;
            totalCalories += calories;
            if (calories > maxCalories) maxCalories = calories;
            if (calories < minCalories) minCalories = calories;
            totalDistance += distance;
            if (distance > maxDistance) maxDistance = distance;
            if (distance < minDistance) minDistance = distance;
            totalTime += time;
            if (time > maxTime) maxTime = time;
            if (time < minTime) minTime = time;
            if (compareMap.containsKey(type)) compareMap.put(type, compareMap.get(type) + 1);
            else compareMap.put(type, 1);
        }
        int favouriteTypeCount = 0;
        for (Map.Entry<PracticeType, Integer> type: compareMap.entrySet()) {
            if (type.getValue() > favouriteTypeCount) {
                favouriteType = type.getKey();
                favouriteTypeCount = type.getValue();
            }
        }
        float averagedCalories = totalCalories / currentData.size();
        float averagedDistance = totalDistance / currentData.size();
        int averagedTime = totalTime / currentData.size();

        TextView totalCaloriesTxt = view.findViewById(R.id.total_all_calories);
        TextView averagedCaloriesTxt = view.findViewById(R.id.total_average_calories);
        TextView maxCaloriesTxt = view.findViewById(R.id.total_biggest_calories);
        TextView minCaloriesTxt = view.findViewById(R.id.total_smallest_calories);
        TextView totalDistanceTxt = view.findViewById(R.id.total_all_distance);
        TextView averagedDistanceTxt = view.findViewById(R.id.total_average_distance);
        TextView maxDistanceTxt = view.findViewById(R.id.total_biggest_distance);
        TextView minDistanceTxt = view.findViewById(R.id.total_smallest_distance);
        TextView totalTimeTxt = view.findViewById(R.id.total_all_time);
        TextView averagedTimeTxt = view.findViewById(R.id.total_average_time);
        TextView maxTimeTxt = view.findViewById(R.id.total_biggest_time);
        TextView minTimeTxt = view.findViewById(R.id.total_smallest_time);
        TextView favouriteTypeTxt = view.findViewById(R.id.total_favourite_workout);

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
        assert favouriteType != null;
        favouriteTypeTxt.setText(formatter.formatType(favouriteType));
    }
}
