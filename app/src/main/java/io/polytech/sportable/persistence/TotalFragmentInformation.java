package io.polytech.sportable.persistence;

import io.polytech.sportable.models.practice.PracticeType;

public class TotalFragmentInformation {

    private final float totalCalories;
    private final float averagedCalories;
    private final float maxCalories;
    private final float minCalories;
    private final float totalDistance;
    private final float averagedDistance;
    private final float maxDistance;
    private final float minDistance;
    private final String totalTime;
    private final String averagedTime;
    private final String maxTime;
    private final String minTime;
    private final PracticeType favouriteType;

    public TotalFragmentInformation(
            float totalCalories,
            float averagedCalories,
            float maxCalories,
            float minCalories,
            float totalDistance,
            float averagedDistance,
            float maxDistance,
            float minDistance,
            int totalTime,
            int averagedTime,
            int maxTime,
            int minTime,
            PracticeType favouriteType
    ) {
        this.totalCalories = totalCalories;
        this.averagedCalories = averagedCalories;
        this.maxCalories = maxCalories;
        this.minCalories = minCalories;
        this.totalDistance = totalDistance;
        this.averagedDistance = averagedDistance;
        this.maxDistance = maxDistance;
        this.minDistance = minDistance;
        this.totalTime = String.format(
                "%s:%s:%s",
                totalTime / 3600000,
                totalTime / 60000,
                totalTime / 1000
        );
        this.averagedTime = String.format(
                "%s:%s:%s",
                averagedTime / 3600000,
                averagedTime / 60000,
                averagedTime / 1000
        );
        this.maxTime = String.format(
                "%s:%s:%s",
                maxTime / 3600000,
                maxTime / 60000,
                maxTime / 1000
        );
        this.minTime = String.format(
                "%s:%s:%s",
                minTime / 3600000,
                minTime / 60000,
                minTime / 1000
        );
        this.favouriteType = favouriteType;
    }

    public float getTotalCalories() {
        return totalCalories;
    }

    public float getAveragedCalories() {
        return averagedCalories;
    }

    public float getMaxCalories() {
        return maxCalories;
    }

    public float getMinCalories() {
        return minCalories;
    }

    public float getTotalDistance() {
        return totalDistance;
    }

    public float getAveragedDistance() {
        return averagedDistance;
    }

    public float getMaxDistance() {
        return maxDistance;
    }

    public float getMinDistance() {
        return minDistance;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public String getAveragedTime() {
        return averagedTime;
    }

    public String getMaxTime() {
        return maxTime;
    }

    public String getMinTime() {
        return minTime;
    }

    public PracticeType getFavouriteType() {
        return favouriteType;
    }
}
