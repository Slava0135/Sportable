package io.polytech.sportable.persistence;

import androidx.room.TypeConverter;

import io.polytech.sportable.models.practice.PracticeType;

public class PracticeTypeConverter {

    @TypeConverter
    public String fromPracticeType(PracticeType type) {
        return type.toString();
    }

    @TypeConverter
    public PracticeType toPracticeType(String data) {
        return PracticeType.valueOf(data);
    }
}
