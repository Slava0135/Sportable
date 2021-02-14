package io.polytech.sportable.model.activities;

public class ActivityRecord {

    final float distance, speed;
    final ActivityType activityType;

    public ActivityRecord(float distance, float speed, ActivityType activityType) {
        this.distance = distance;
        this.speed = speed;
        this.activityType = activityType;
    }

}
