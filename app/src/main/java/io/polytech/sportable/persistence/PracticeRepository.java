package io.polytech.sportable.persistence;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.polytech.sportable.models.practice.PracticeType;

public class PracticeRepository {

    private PracticeDao mPracticeDao;
    private LiveData<List<PracticeResult>> mAllPractices;

    public PracticeRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mPracticeDao = db.practiceDao();
        mAllPractices = mPracticeDao.getAll();
    }

    public LiveData<List<PracticeResult>> getAllPractices() {
        return mAllPractices;
    }

    public List<PracticeResult> getAllPracticesAfter(long date) {
        return mPracticeDao.getAllAfter(date);
    }

    public void insert (PracticeResult practiceResult) {
        new insertAsyncTask(mPracticeDao).execute(practiceResult);
    }

    public TotalFragmentInformation createTotalFragmentInformation() {
        List<PracticeResult> practices = mAllPractices.getValue();
        float totalCalories = 0.f;
        float maxCalories = 0.f;
        float minCalories = Float.MAX_VALUE;
        float totalDistance = 0.f;
        float maxDistance = 0.f;
        float minDistance = Float.MAX_VALUE;
        int totalTime = 0;
        int maxTime = 0;
        int minTime = Integer.MAX_VALUE;
        PracticeType favouriteType = null;
        Map<PracticeType, Integer> compareMap = new HashMap<>();
        for (PracticeResult practice: practices) {
            float calories = practice.calories;
            float distance = practice.distance;
            int time = practice.time;
            PracticeType type = practice.practiceType;
            totalCalories += calories;
            if (calories > maxCalories) maxCalories = calories;
            if (calories < minCalories) minCalories = calories;
            totalDistance += distance;
            if (distance > maxDistance) maxDistance = distance;
            if (distance > minDistance) minDistance = distance;
            totalTime += time;
            if (time > maxTime) maxTime = time;
            if (time < minTime) minTime = time;
            if (compareMap.containsKey(type)) compareMap.put(type, compareMap.get(type) + 1);
            else compareMap.put(type, 1);
        }
        int favouriteTypeCount = 0;
        for (Map.Entry<PracticeType, Integer> type: compareMap.entrySet()) {
            if (type.getValue() > favouriteTypeCount) {
                favouriteType = type.getKey();
                favouriteTypeCount = type.getValue();
            }
        }
        return new TotalFragmentInformation(
                totalCalories,
                totalCalories / practices.size(),
                maxCalories,
                minCalories,
                totalDistance,
                totalCalories / practices.size(),
                maxDistance,
                minDistance,
                totalTime,
                totalTime / practices.size(),
                maxTime,
                minTime,
                favouriteType
                );
    }

    public void deleteAll()  {
        new deleteAllAsyncTask(mPracticeDao).execute();
    }

    private static class insertAsyncTask extends AsyncTask<PracticeResult, Void, Void> {

        private PracticeDao mAsyncTaskDao;

        insertAsyncTask(PracticeDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PracticeResult... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private PracticeDao mAsyncTaskDao;

        deleteAllAsyncTask(PracticeDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }


}

