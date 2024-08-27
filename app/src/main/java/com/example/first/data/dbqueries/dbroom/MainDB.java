package com.example.first.data.dbqueries.dbroom;

import android.content.Context;

import androidx.room.Room;

import lombok.Getter;

public class MainDB{
    @Getter
    private AppDatabase appDatabase;
    private static MainDB instance = null;
    
    private MainDB(Context context){
        appDatabase = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "favorites.db"
                        ).build();
    }

    public static MainDB getInstance(Context context) {
        if (instance == null) instance = new MainDB(context);
        return instance;
    }
}
