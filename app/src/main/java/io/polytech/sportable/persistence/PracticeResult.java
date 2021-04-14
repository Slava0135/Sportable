package io.polytech.sportable.persistence;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import io.polytech.sportable.models.practice.PracticeType;

@Entity(tableName = "practice_table")
public class PracticeResult {

    @PrimaryKey
    @ColumnInfo(name = "date")
    public final long date; // Дата тренировки

    @ColumnInfo(name = "distance")
    public final float distance; // Проеодолённая дистанция

    @ColumnInfo(name = "calories")
    public final float calories; // Сожённые каллории

    @ColumnInfo(name = "time")
    public final int time; // Затраченное на тренировку время

    @ColumnInfo(name = "practiceType")
    public final PracticeType practiceType; // Вид проведённой тренировки

    public PracticeResult(long date, float distance, float calories, int time, PracticeType practiceType) {
        this.date = date;
        this.distance = distance;
        this.calories = calories;
        this.time = time;
        this.practiceType = practiceType;
    }

    public String getTime() {
        return String.format("%s:%s:%s", time / 3600000, time / 60000, time / 1000);
    }
}

