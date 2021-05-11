package io.polytech.sportable.persistence;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.polytech.sportable.models.practice.PracticeType;

public class PracticeResultViewModel extends AndroidViewModel {

    public LiveData<List<PracticeResult>> allData;
    private PracticeRepository rep;

    public PracticeResultViewModel(@NonNull Application application) {
        super(application);
        rep = new PracticeRepository(application);
        allData = rep.getAllPractices();
    }

    public LiveData<List<PracticeResult>> getAllByPractice(PracticeType practiceType) {
        return rep.getAllByPractice(practiceType);
    }

    public LiveData<List<PracticeResult>> getByDate(long date) {
        return rep.getByDate(date);
    }

    public void insert(PracticeResult res) {
        rep.insert(res);
    }
}
