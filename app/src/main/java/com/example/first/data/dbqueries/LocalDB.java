package com.example.first.data.dbqueries;

import android.content.Context;
import android.util.Log;

import androidx.loader.app.LoaderManager;

import com.example.first.data.dbqueries.dbroom.MainDB;
import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.IDbQueries;
import com.example.first.domain.interfaces.ILocalDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
