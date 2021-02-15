package io.polytech.sportable.models.practice;

public class PracticeRecord {

    final float distance, speed, calories;
    final PracticeType activityType;

    public PracticeRecord(float distance, float speed, PracticeType activityType) {
        this.distance = distance;
        this.speed = speed;
        this.activityType = activityType;
        this.calories = calculateCalories();
    }

    private float calculateCalories() {
        return 0f;
    }

}
