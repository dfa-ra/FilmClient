package com.example.first.data.dbqueries.dbroom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.first.data.models.mainModel.FilmModel;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MyDao {
    @Insert
    void insertFilm(FilmModel film);



    @Transaction
    @Query("SELECT * FROM films")
    Flowable<List<FilmModel>> getAllFilmDetails();

    @Transaction
    @Query("SELECT * FROM films WHERE kinopoiskId = :id")
    Single<FilmModel> getFilmsById(int id);

    @Transaction
    @Query("SELECT * FROM films WHERE (nameRu = :text OR nameEn = :text OR nameOriginal = :text)")
    Flowable<List<FilmModel>> getFilmsByText(String text);

    @Transaction
    @Query("SELECT * FROM films WHERE collection = :collection")
    Flowable<List<FilmModel>> getFilmsByCollection(String collection);

    @Transaction
    @Query("DELETE FROM films WHERE kinopoiskId = :id")
    void deleteById(int id);

    @Transaction
    @Query("UPDATE films SET isReadable = :isReadable WHERE kinopoiskId = :id")
    void updateIsReadable(int id, boolean isReadable);

    @Transaction
    @Query("UPDATE films SET comment = :comment WHERE kinopoiskId = :id")
    void updateComment(int id, String comment);

    @Transaction
    @Query("DELETE FROM films")
    void deleteAll();
}
