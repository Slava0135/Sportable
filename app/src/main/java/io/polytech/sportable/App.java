package io.polytech.sportable;

import android.app.Application;

import androidx.room.Room;

import io.polytech.sportable.persistence.AppDatabase;

class AppSportable extends Application {

    public final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "database-name").build();

}
