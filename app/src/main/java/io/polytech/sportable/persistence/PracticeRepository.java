package io.polytech.sportable.persistence;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

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

