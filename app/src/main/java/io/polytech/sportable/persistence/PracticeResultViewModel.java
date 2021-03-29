package io.polytech.sportable.persistence;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PracticeResultViewModel extends AndroidViewModel {

    public LiveData<List<PracticeResult>> allData;
    private PracticeRepository rep;

    public PracticeResultViewModel(@NonNull Application application) {
        super(application);
        PracticeDao practiceDao = AppDatabase.getDatabase(application).practiceDao();
        rep = new PracticeRepository(application);
        allData = rep.getAllPractices();
    }

}
