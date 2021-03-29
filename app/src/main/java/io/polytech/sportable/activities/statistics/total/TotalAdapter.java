package io.polytech.sportable.activities.statistics.total;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.polytech.sportable.R;
import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.persistence.PracticeResult;

public class TotalAdapter extends RecyclerView.Adapter<TotalAdapter.TotalViewHolder> {

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

    static class TotalViewHolder extends RecyclerView.ViewHolder {

        TextView totalCaloriesTxt, averagedCaloriesTxt, maxCaloriesTxt, minCaloriesTxt;
        TextView totalDistanceTxt, averagedDistanceTxt, maxDistanceTxt, minDistanceTxt;
        TextView totalTimeTxt, averagedTimeTxt, maxTimeTxt, minTimeTxt;
        TextView favouriteTypeTxt;

        public TotalViewHolder(@NonNull View itemView) {
            super(itemView);
            totalCaloriesTxt = itemView.findViewById(R.id.total_all_calories);
            averagedCaloriesTxt = itemView.findViewById(R.id.total_average_calories);
            maxCaloriesTxt = itemView.findViewById(R.id.total_biggest_calories);
            minCaloriesTxt = itemView.findViewById(R.id.total_smallest_calories);
            totalDistanceTxt = itemView.findViewById(R.id.total_all_distance);
            averagedDistanceTxt = itemView.findViewById(R.id.total_average_distance);
            maxDistanceTxt = itemView.findViewById(R.id.total_biggest_distance);
            minDistanceTxt = itemView.findViewById(R.id.total_smallest_distance);
            totalTimeTxt = itemView.findViewById(R.id.total_all_time);
            averagedTimeTxt = itemView.findViewById(R.id.total_average_time);
            maxTimeTxt = itemView.findViewById(R.id.total_biggest_time);
            minTimeTxt = itemView.findViewById(R.id.total_smallest_time);
            favouriteTypeTxt = itemView.findViewById(R.id.total_favourite_workout);
        }
    }

    @NonNull
    @Override
    public TotalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return
                new TotalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.total_fragment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TotalViewHolder holder, int position) {
        holder.totalCaloriesTxt.setText(String.valueOf(totalCalories));
        holder.averagedCaloriesTxt.setText(String.valueOf(averagedCalories));
        holder.maxCaloriesTxt.setText(String.valueOf(maxCalories));
        holder.minCaloriesTxt.setText(String.valueOf(minCalories));
        holder.totalDistanceTxt.setText(String.valueOf(totalDistance));
        holder.averagedDistanceTxt.setText(String.valueOf(averagedDistance));
        holder.maxDistanceTxt.setText(String.valueOf(maxDistance));
        holder.minDistanceTxt.setText(String.valueOf(minDistance));
        holder.totalTimeTxt.setText(String.valueOf(totalTime));
        holder.averagedTimeTxt.setText(String.valueOf(averagedTime));
        holder.maxTimeTxt.setText(String.valueOf(maxTime));
        holder.minTimeTxt.setText(String.valueOf(minTime));
        holder.favouriteTypeTxt.setText(String.valueOf(favouriteType));
    }

    @Override
    public int getItemCount() {
        return practicesList.size();
    }

    public void setData(List<PracticeResult> practices) {
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
            if (distance > minDistance) minDistance = distance;
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
    }
}
