package io.polytech.sportable.model;

public class ActivityRecord {

    final float distance, speed, calories;
    final ActivityType activityType;

    public ActivityRecord(float distance, float speed, float calories, ActivityType activityType) {
        this.distance = distance;
        this.speed = speed;
        this.calories = calories;
        this.activityType = activityType;
    }

}
