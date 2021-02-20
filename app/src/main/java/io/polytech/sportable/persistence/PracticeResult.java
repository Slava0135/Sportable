package io.polytech.sportable.persistence;

import java.util.Date;

public class PracticeResult {

    private final float distance;
    private final float calories;
    private final String time;
    private final long timeInMillies; //передаётся в миллисекундах и будет использоваться для удобного расчёта диаграмм

    public PracticeResult(float distance, float calories, long time) {
        this.distance = distance;
        this.calories = calories;
        this.timeInMillies = time;
        this.time = String.format("%s:%s:%s", time / 3600000, time / 60000, time / 1000);
    }

    public float getDistance() {
        return distance;
    }

    public float getCalories() {
        return calories;
    }

    public long getTimeInMillies() {
        return timeInMillies;
    }

    public String getTime() {
        return time;
    }
}

