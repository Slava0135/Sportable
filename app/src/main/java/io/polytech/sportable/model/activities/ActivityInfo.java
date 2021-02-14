package io.polytech.sportable.model.activities;

public class ActivityInfo {

    final float distance, speed, calories;
    final ActivityType activityType;

    public ActivityInfo(float distance, float speed, float calories, ActivityType activityType) {
        this.distance = distance;
        this.speed = speed;
        this.calories = calories;
        this.activityType = activityType;
    }

}
