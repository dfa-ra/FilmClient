package com.example.first.data.dbqueries.dbroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.first.data.models.mainModel.FilmModel;


@Database(entities = {FilmModel.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract Dao filmDao();
}
