package io.polytech.sportable.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PracticeResult.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PracticeDao practiceDao();
}
