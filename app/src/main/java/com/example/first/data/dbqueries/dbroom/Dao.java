package com.example.first.data.dbqueries.dbroom;


import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.first.data.models.mainModel.FilmModel;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@androidx.room.Dao
public interface Dao {
    @Insert
    void insertFilm(FilmModel film);

    @Transaction
    @Query("SELECT * FROM films")
    Flowable<List<FilmModel>> getAllFilmDetails();

    @Transaction
    @Query("SELECT * FROM films WHERE kinopoiskId = :id")
    Single<FilmModel> getFilmsById(int id);

    @Transaction
    @Query("DELETE FROM films WHERE kinopoiskId = :id")
    void deleteById(int id);

    @Transaction
    @Query("DELETE FROM films")
    void deleteAll();
}
