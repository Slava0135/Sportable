package io.polytech.sportable.models.practice;

import android.content.SharedPreferences;

import com.yandex.mapkit.location.Location;
import com.yandex.mapkit.location.LocationListener;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class UserModel {

    private final int height;
    private final float weight;

    public UserModel(SharedPreferences settings) {
        this.height = settings.getInt("height", 0);
        this.weight = settings.getFloat("weight", 0);
    }
    // наше
//    public double getSin(android.location.Location /*класс местоположения*/ start, android.location.Location /*класс местоположения*/ end) {
//        double z1 = start.getAltitude();
//        double z2 = end.getAltitude();
//        float distance = start.distanceTo(end);
//        double hypotenuse = sqrt(pow(z1 - z2, 2) + pow(distance, 2));
//       return abs(z1 - z2) / hypotenuse;
//    }
//
//    public boolean getIsUp(android.location.Location /*класс местоположения*/ start, android.location.Location /*класс местоположения*/ end) {
//        double z1 = start.getAltitude();
//        double z2 = end.getAltitude();
//        return (z2 >= z1);
//    }
//
//    public float getCalories(PracticeType type, int time, float velocity, android.location.Location /*класс местоположения*/ start, android.location.Location /*класс местоположения*/ end) {
//        final boolean isUp = getIsUp(start, end);
//        final float sin = (float) getSin(start, end);
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
//            default: {
//                return 0f;
//            }
//        }
//    }
// оригинал
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
            default: {
                return 0f;
            }
        }
    }
}
