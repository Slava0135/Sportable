package io.polytech.sportable.models.practice;

import android.content.SharedPreferences;

public class UserModel {

    private final int height;
    private final float weight;

//    private final float sin = getSin();
//    private final boolean isUp;

    public UserModel(SharedPreferences settings) {
        this.height = settings.getInt("height", 0);
        this.weight = settings.getFloat("weight", 0);
    }

//    public float getSin(/*класс местоположения*/ start, /*класс местоположения*/ end) {
//        z1 = start.getAltitude();
//        z2 = end.getAltitude();
//        Hypotenuse = getDistance();
//        this.isUp = (z2 >= z1);
//        return abs(z1 - z2) / Hypotenuse;
//    }
//
//
//
//    public float newGetCalories(PracticeType type, int time, float velocity) {
//        if (velocity == 0) return 0f;
//        switch (type) {
//            case Walk: {
//                if (isUp) {
//                    return time / 60f * (0.035f * weight + 0.029f * (velocity * velocity / height) * weight)*(1f + 0.7f * sin);
//                } else {
//                    return time / 60f * (0.035f * weight + 0.029f * (velocity * velocity / height) * weight)*(1f - 1.3f * sin);
//                }
//
//            }
//            case Run: {
//                if (isUp) {
//                    return time / 60f * (0.035f * weight + 0.029f * (velocity * velocity / height) * weight)*(1f + 0.7f * sin);
//                } else {
//                    return time / 60f * (0.035f * weight + 0.029f * (velocity * velocity / height) * weight)*(1f - 1.3f * sin);
//                }
//            }
//            case Bicycle: {
//                return time / 60f * (5 * weight * 3.5f) / 200;
//            }
//            case Skies: {
//                return time / 60f * (7 * weight * 3.5f) / 200;
//            }
//            default: {
//                return 0f;
//            }
//        }
//    }

    public float getCalories(PracticeType type, int time, float velocity) {
        if (velocity == 0) return 0f;
        switch (type) {
            case Walk: {
                return time / 60f * (0.035f * weight + 0.029f * (velocity * velocity / height) * weight);
            }
            case Run: {
                return time / 60f * (0.035f * weight + 0.029f * (velocity * velocity / height) * weight);
            }
            case Bicycle: {
                return time / 60f * (5 * weight * 3.5f) / 200;
            }
            case Skies: {
                return time / 60f * (7 * weight * 3.5f) / 200;
            }
            default: {
                return 0f;
            }
        }
    }
}
