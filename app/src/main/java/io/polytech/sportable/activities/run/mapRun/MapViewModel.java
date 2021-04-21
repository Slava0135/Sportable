package io.polytech.sportable.activities.run.mapRun;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.gms.location.FusedLocationProviderClient;

import io.polytech.sportable.models.practice.PracticeType;

public class MapViewModel extends AndroidViewModel {

    FusedLocationProviderClient fusedLocationClient;

    boolean autoCreate;

    PracticeType practiceType;

    Units unit = Units.meters;
    
    public MapViewModel(@NonNull Application application) {
        super(application);
    }

    enum Units {
        meters,
        minutes,
        calories;

        float calculateDistance(float distance) {
            switch (this) {
                case minutes: return 0;
                case calories: return 0;
                default: return distance;
            }
        }

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
