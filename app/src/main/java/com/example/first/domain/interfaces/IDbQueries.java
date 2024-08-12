package com.example.first.domain.interfaces;

import android.util.Log;

import com.example.first.data.models.mainModel.FilmModel;

import java.util.ArrayList;
import java.util.List;

public interface IDbQueries {

    void clearLocalBd();

    void selectById(Integer id);

    void addNewFilm(FilmModel film);

    FilmModel getLocalFilm(Integer id);

    FilmModel getFilm(Integer id);

    List<FilmModel> getFilms();

    boolean isFilmHere(FilmModel film);

}
