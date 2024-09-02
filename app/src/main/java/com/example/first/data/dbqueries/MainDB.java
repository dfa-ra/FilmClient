package com.example.first.data.dbqueries;

import android.content.Context;

import androidx.room.Room;

import com.example.first.data.dbqueries.dbroom.AppDatabase;
import com.example.first.domain.interfaces.IDbQueries;

public class MainDB implements IDbQueries {

    private AppDatabase appDatabase;

    public MainDB(Context context){
        appDatabase = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "favorites.db"
                        )
                        .fallbackToDestructiveMigration()
                        .build();
    }

    @Override
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
