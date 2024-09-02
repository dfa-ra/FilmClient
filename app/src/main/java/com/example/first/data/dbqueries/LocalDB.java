package com.example.first.data.dbqueries;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.ILocalDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocalDB implements ILocalDB {
    private static String Tag = "LocalDBTAG";

    private final HashMap<Integer, FilmModel> localFilms = new HashMap<>();

    public LocalDB(){}

    @Override
    public List<FilmModel> getLocalFilms() {
        return new ArrayList<>(localFilms.values());
    }

    @Override
    public void addFilm(FilmModel film){
        localFilms.put(film.kinopoiskId, film);
    }

    @Override
    public void addFilms(List<FilmModel> films){
        for (FilmModel film: films) localFilms.put(film.kinopoiskId, film);
    }

    @Override
    public FilmModel getFilmById(int id){
        return localFilms.get(id);
    }

    @Override
    public void clearLocalBd() {
        localFilms.clear();
    }
}
