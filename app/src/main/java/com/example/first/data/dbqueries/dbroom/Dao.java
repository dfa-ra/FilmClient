package com.example.first.data.dbqueries.dbroom;


import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.first.data.models.mainModel.FilmModel;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert
    void insertFilm(FilmModel film);

    @Transaction
    @Query("SELECT * FROM films")
    List<FilmModel> getAllFilmDetails();

    @Transaction
    @Query("SELECT * FROM films WHERE id = :id")
    LiveData<List<FilmModel>> getFilmsById(int id);

    @Transaction
    @Query("DELETE FROM films WHERE id = :id")
    void deleteById(int id);

    @Transaction
    @Query("DELETE FROM films")
    void deleteAll();
}
