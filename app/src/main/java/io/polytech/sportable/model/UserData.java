package io.polytech.sportable.model;

import io.polytech.sportable.model.activities.ActivityRecord;

public class UserData {

    private final float weight, height;

    public UserData(float weight, float height) {
        this.weight = weight;
        this.height = height;
    }

    static UserData loadUserData() {
        return new UserData(0, 0);
    }

    static void saveUserData(UserData data) { }

    public float calculateCalories(ActivityRecord record) {
        return 0f;
    }

}
