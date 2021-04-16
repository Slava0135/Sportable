package io.polytech.sportable.activities.statistics.total;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.polytech.sportable.R;
import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.persistence.PracticeResult;

import static java.lang.String.format;

public class TotalAdapter {

    private final View view;

    private List<PracticeResult> practicesList = new ArrayList<>();
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
    private PracticeType favouriteType;

    private TextView totalCaloriesTxt, averagedCaloriesTxt, maxCaloriesTxt, minCaloriesTxt;
    private TextView totalDistanceTxt, averagedDistanceTxt, maxDistanceTxt, minDistanceTxt;
    private TextView totalTimeTxt, averagedTimeTxt, maxTimeTxt, minTimeTxt;
    private TextView favouriteTypeTxt;


    public TotalAdapter(View view) {
        this.view = view;
    }

    @SuppressLint("SetTextI18n") //Исправвить
    public void setData(List<PracticeResult> practices) {
        if (practices.isEmpty()) {
            practices.add(
                    new PracticeResult(
                            0,
                            0.f,
                            0.f,
                            0,
                            PracticeType.Nothing
                    )
            );
        }
        this.practicesList = practices;
        totalCalories = 0.f;
        maxCalories = 0.f;
        minCalories = Float.MAX_VALUE;
        totalDistance = 0.f;
        maxDistance = 0.f;
        minDistance = Float.MAX_VALUE;
        totalTime = 0;
        maxTime = 0;
        minTime = Integer.MAX_VALUE;
        favouriteType = null;
        Map<PracticeType, Integer> compareMap = new HashMap<>();
        for (PracticeResult practice: practicesList) {
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
        averagedCalories = totalCalories / practicesList.size();
        averagedDistance = totalDistance / practicesList.size();
        averagedTime = totalTime / practicesList.size();

        totalCaloriesTxt = view.findViewById(R.id.total_all_calories);
        averagedCaloriesTxt = view.findViewById(R.id.total_average_calories);
        maxCaloriesTxt = view.findViewById(R.id.total_biggest_calories);
        minCaloriesTxt = view.findViewById(R.id.total_smallest_calories);
        totalDistanceTxt = view.findViewById(R.id.total_all_distance);
        averagedDistanceTxt = view.findViewById(R.id.total_average_distance);
        maxDistanceTxt = view.findViewById(R.id.total_biggest_distance);
        minDistanceTxt = view.findViewById(R.id.total_smallest_distance);
        totalTimeTxt = view.findViewById(R.id.total_all_time);
        averagedTimeTxt = view.findViewById(R.id.total_average_time);
        maxTimeTxt = view.findViewById(R.id.total_biggest_time);
        minTimeTxt = view.findViewById(R.id.total_smallest_time);
        favouriteTypeTxt = view.findViewById(R.id.total_favourite_workout);

        totalCaloriesTxt.setText(totalCalories + " кал.");
        averagedCaloriesTxt.setText(format("%.2f", averagedCalories) + " кал.");
        maxCaloriesTxt.setText(maxCalories + " кал.");
        minCaloriesTxt.setText(minCalories + " кал.");
        totalDistanceTxt.setText(format(
                "%s",
                String.valueOf(totalDistance).split("\\.")[0]
                ) + " км." + format(
                        "%s",
                String.valueOf(totalDistance).split("\\.")[1]
        ) + " м.");
        averagedDistanceTxt.setText(format(
                "%s",
                String.valueOf(averagedDistance).split("\\.")[0]
        ) + " км." + format(
                "%s",
                String.valueOf(averagedDistance).split("\\.")[1]
        ) + " м.");
        maxDistanceTxt.setText(format(
                "%s",
                String.valueOf(maxDistance).split("\\.")[0]
        ) + " км." + format(
                "%s",
                String.valueOf(maxDistance).split("\\.")[1]
        ) + " м.");
        minDistanceTxt.setText(format(
                "%s",
                String.valueOf(minDistance).split("\\.")[0]
        ) + " км." + format(
                "%s",
                String.valueOf(minDistance).split("\\.")[1]
        ) + " м.");
        totalTimeTxt.setText(String.format(
                "%s:%s:%s",
                totalTime / 3600000,
                totalTime / 60000,
                totalTime / 1000
        ));
        averagedTimeTxt.setText(String.format(
                "%s:%s:%s",
                averagedTime / 3600000,
                averagedTime / 60000,
                averagedTime / 1000
        ));
        maxTimeTxt.setText(String.format(
                "%s:%s:%s",
                maxTime / 3600000,
                maxTime / 60000,
                maxTime / 1000
        ));
        minTimeTxt.setText(String.format(
                "%s:%s:%s",
                minTime / 3600000,
                minTime / 60000,
                minTime / 1000
        ));
        switch (favouriteType) {
            case Run:
            {
                favouriteTypeTxt.setText("Бег");
                break;
            }
            case Walk:
            {
                favouriteTypeTxt.setText("Ходьба");
                break;
            }
            case Bicycle:
            {
                favouriteTypeTxt.setText("Велосипед");
                break;
            }
            case Skies:
            {
                favouriteTypeTxt.setText("Лыжи");
                break;
            }
            case Nothing:
            {
                favouriteTypeTxt.setText("Нет");
                break;
            }
        }
    }
}
