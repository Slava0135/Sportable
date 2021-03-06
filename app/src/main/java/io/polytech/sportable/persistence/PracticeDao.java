package io.polytech.sportable.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PracticeDao {

    @Query("SELECT * from practice_table ORDER BY date DESC")
    LiveData<List<PracticeResult>> getAll();

    @Query("SELECT * from practice_table WHERE date >= :date ORDER BY date DESC")
    List<PracticeResult> getAllAfter(long date);

    @Query("DELETE FROM practice_table")
    void deleteAll();

    @Insert
    void insert(PracticeResult result);
}
