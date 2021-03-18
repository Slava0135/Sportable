package io.polytech.sportable.activities.statistics;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.persistence.PracticeRepository;
import io.polytech.sportable.persistence.PracticeResult;
import io.polytech.sportable.persistence.TotalFragmentInformation;

public class StatViewModel extends AndroidViewModel {

    private PracticeRepository localRepository;
    private LiveData<List<PracticeResult>> allPractices;

    public StatViewModel (Application app) {
        super(app);
        localRepository =  new PracticeRepository(app);
        allPractices = localRepository.getAllPractices();
    }

    LiveData<List<PracticeResult>> getAllPractices() {
        return allPractices;
    }

    public void insert(PracticeResult practiceResult) {
        localRepository.insert(practiceResult);
    }

    public TotalFragmentInformation createForTotalFragment() {
        return localRepository.createTotalFragmentInformation();
    }
}
