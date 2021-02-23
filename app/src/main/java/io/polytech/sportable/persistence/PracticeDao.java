package io.polytech.sportable.persistence;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface PracticeDao {
    @Query("SELECT * FROM practiceresult")
    List<PracticeResult> getAll();

    @Insert
    void insertAll(PracticeResult... results);
}
