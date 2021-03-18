package io.polytech.sportable.persistence;

import io.polytech.sportable.models.practice.PracticeType;

public class CertainDayFragmentInformation {

    private final PracticeType practiceType;
    private final float calories;
    private final float distance;
    private final float speed;
    private final String time;

    public CertainDayFragmentInformation(
            PracticeType practiceType,
            float calories,
            float distance,
            float speed,
            int time
    ) {
        this.practiceType = practiceType;
        this.calories = calories;
        this.distance = distance;
        this.speed = speed;
        this.time = String.format(
                "%s:%s:%s",
                time / 3600000,
                time / 60000,
                time / 1000
        );
    }

    public PracticeType getPracticeType() {
        return practiceType;
    }

    public float getCalories() {
        return calories;
    }

    public float getDistance() {
        return distance;
    }

    public float getSpeed() {
        return speed;
    }

    public String getTime() {
        return time;
    }
}
