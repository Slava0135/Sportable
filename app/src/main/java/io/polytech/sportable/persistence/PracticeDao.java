package io.polytech.sportable.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.polytech.sportable.models.practice.PracticeType;

@Dao
public interface PracticeDao {

    @Query("SELECT * from practice_table ORDER BY date DESC")
    LiveData<List<PracticeResult>> getAll();

    @Query("SELECT * from practice_table WHERE practiceType == :practiceType ORDER BY date DESC")
    LiveData<List<PracticeResult>> getAllByPractice(PracticeType practiceType);

    @Query("SELECT * FROM practice_table WHERE date == :date")
    LiveData<PracticeResult> getByDate(long date);

    @Query("DELETE FROM practice_table")
    void deleteAll();

    @Insert
    void insert(PracticeResult result);
}
