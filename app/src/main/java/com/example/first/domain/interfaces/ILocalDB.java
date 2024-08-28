package com.example.first.domain.interfaces;

import com.example.first.data.models.mainModel.FilmModel;

import java.util.ArrayList;
import java.util.List;

public interface ILocalDB {
    List<FilmModel> getLocalFilms();
    void addFilm(FilmModel film);
    void addFilms(List<FilmModel> films);
    FilmModel getFilmById(int id);
    void clearLocalBd();
}
