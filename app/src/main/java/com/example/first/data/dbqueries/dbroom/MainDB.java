package com.example.first.data.dbqueries.dbroom;

import android.content.Context;

import androidx.room.Room;

import com.example.first.domain.interfaces.IDbQueries;

import lombok.Getter;

public class MainDB implements IDbQueries {

    private AppDatabase appDatabase;

    public MainDB(Context context){
        appDatabase = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "favorites.db"
                        ).build();
    }

    @Override
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
