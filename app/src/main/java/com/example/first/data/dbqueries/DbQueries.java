package com.example.first.data.dbqueries;

import android.util.Log;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.IDbQueries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbQueries implements IDbQueries {
    private static String Tag = "DbQueries";

    private final HashMap<Integer, FilmModel> localFilms = new HashMap<>();
    private final HashMap<Integer, FilmModel> films = new HashMap<>();

    public DbQueries(){}

    @Override
    public void clearLocalBd() {
        localFilms.clear();
    }

    @Override
    public void selectById(Integer id){
        films.put(id, localFilms.get(id));
    }

    @Override
    public void addNewFilm(FilmModel film){
        Log.i(Tag, "add new item");
        localFilms.put(film.getKinopoiskId(), film);
    }

    @Override
    public FilmModel getLocalFilm(Integer id){
        Log.i("aa99", String.valueOf(id));
        Log.i("aa99", localFilms.toString());
        return localFilms.get(id);
    }

    @Override
    public FilmModel getFilm(Integer id){
        return films.get(id);
    }

    @Override
    public List<FilmModel> getFilms() {
        return new ArrayList<>(films.values());
    }

    @Override
    public boolean isFilmHere(FilmModel film){
        return films.containsKey(film.kinopoiskId);
    }

}
