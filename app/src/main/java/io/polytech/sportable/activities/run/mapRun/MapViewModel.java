package io.polytech.sportable.activities.run.mapRun;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.List;

import io.polytech.sportable.models.practice.PracticeType;
import io.polytech.sportable.persistence.PracticeRepository;
import io.polytech.sportable.persistence.PracticeResult;

public class MapViewModel extends AndroidViewModel {

    FusedLocationProviderClient fusedLocationClient;
    boolean autoCreate = true;
    PracticeType practiceType;
    Units unit = Units.meters;
    PracticeRepository repository;
    List<PracticeResult> records;
    
    public MapViewModel(@NonNull Application application) {
        super(application);
        repository = new PracticeRepository(application);
    }

    float calculateDistance(float units) {
        switch (unit) {
            case minutes: {
                float average = 0;
                int count = 0;
                for (PracticeResult record : records){
                    if (record.distance > 0) {
                        average += record.distance / record.time;
                        count++;
                    }
                }
                if (count > 0) {
                    return 60f * units * average / count;
                }
                break;
            }
            case calories: {
                float average = 0;
                int count = 0;
                for (PracticeResult record : records){
                    if (record.distance > 0) {
                        average += record.distance / record.calories;
                        count++;
                    }
                }
                if (count > 0) {
                    return units * average / count;
                }
                break;
            }
            default: return units;
        }
        return 1000;
    }

    void setRecords(List<PracticeResult> data) {
        records = data;
    }

    enum Units {
        meters,
        minutes,
        calories;

        @Override
        public String toString() {
            switch (this) {
                case meters: return "Метры";
                case minutes: return "Минуты";
                case calories: return "ККал";
                default: return "";
            }
        }
    }
}
