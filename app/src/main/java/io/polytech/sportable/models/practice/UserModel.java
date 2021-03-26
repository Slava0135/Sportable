package io.polytech.sportable.models.practice;

import android.content.SharedPreferences;

public class UserModel {

    private final int height;
    private final float weight;

    public UserModel(SharedPreferences settings) {
        this.height = settings.getInt("height", 0);
        this.weight = settings.getFloat("weight", 0);
    }

    public float getCalories(PracticeType type, int time, float velocity) {
        if (velocity == 0) return 0f;
        switch (type) {
            case Walk: {
                return time / 60f * (0.035f * weight + 0.029f * (velocity * velocity / height) * weight);
            }
            default: {
                return 0f;
            }
        }
    }
}
