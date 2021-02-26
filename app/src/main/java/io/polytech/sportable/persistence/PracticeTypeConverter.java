package io.polytech.sportable.persistence;

import androidx.room.TypeConverter;

import io.polytech.sportable.models.practice.PracticeType;

public class PracticeTypeConverter {
//Возможны баги, так как не вникал в перевод из Enum в String и обратно
    @TypeConverter
    public String fromPracticeType(PracticeType type) {
        return type.toString();
    }

    @TypeConverter
    public PracticeType toPracticeType(String data) {
        return PracticeType.valueOf(data);
    }
}
