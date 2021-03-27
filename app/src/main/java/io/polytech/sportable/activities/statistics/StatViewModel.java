package io.polytech.sportable.activities.statistics;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.persistence.PracticeRepository;
import io.polytech.sportable.persistence.PracticeResult;
import io.polytech.sportable.persistence.TotalFragmentInformation;

public class StatViewModel extends AndroidViewModel {

    private PracticeRepository localRepository;
    private LiveData<List<PracticeResult>> allPractices;
    List<PracticeResult> practices = new ArrayList<>();

    public StatViewModel (Application app, LifecycleOwner owner) {
        super(app);
        localRepository = new PracticeRepository(app);
        allPractices = localRepository.getAllPractices();
        allPractices.observe(owner, new Observer<List<PracticeResult>>() {
            @Override
            public void onChanged(List<PracticeResult> practiceResults) {
                practices = practiceResults;
            }
        });
    }

    LiveData<List<PracticeResult>> getAllPractices() {
        return allPractices;
    }

    public void insert(PracticeResult practiceResult) {
        localRepository.insert(practiceResult);
    }

    public TotalFragmentInformation createTotalFragmentInformation() {
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
        for (PracticeResult practice: practices) {
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
        return new TotalFragmentInformation(
                totalCalories,
                totalCalories / practices.size(),
                maxCalories,
                minCalories,
                totalDistance,
                totalCalories / practices.size(),
                maxDistance,
                minDistance,
                totalTime,
                totalTime / practices.size(),
                maxTime,
                minTime,
                favouriteType
        );
    }
}
