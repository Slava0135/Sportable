package io.polytech.sportable.persistence;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.polytech.sportable.models.practice.PracticeType;

@Entity
abstract public class PracticeResult {

    @PrimaryKey
    @ColumnInfo(name = "date")
    public final long date;

    @ColumnInfo(name = "distance")
    public final float distance;

    @ColumnInfo(name = "calories")
    public final float calories;

    @ColumnInfo(name = "time")
    public final long time;

    @ColumnInfo(name = "practiceType")
    public final PracticeType practiceType;

    public PracticeResult(float distance, float calories, long time, PracticeType type) {
        this.distance = distance;
        this.calories = calories;
        this.time = time;
        this.practiceType = type;
        date = System.currentTimeMillis();
    }

    public String getTime() {
        return String.format("%s:%s:%s", time / 3600000, time / 60000, time / 1000);
    }
}

