package com.example.first.data.dbqueries;

import android.util.Log;

import com.example.first.data.models.mainModel.FilmModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbQueries {
    private static String Tag = "DbQueries";
    private static DbQueries dbQueries = null;

    private final HashMap<Integer, FilmModel> localFilms = new HashMap<>();
    private final HashMap<Integer, FilmModel> films = new HashMap<>();

    public static DbQueries getInstance(){
        if (dbQueries == null) dbQueries = new DbQueries();
        return dbQueries;
    }

    private DbQueries(){}

    public void selectById(Integer id){
        films.put(id, localFilms.get(id));
    }

    public void addNewFilm(FilmModel film){
        Log.i(Tag, "add new item");
        localFilms.put(film.getKinopoiskId(), film);
    }

    public FilmModel getLocalFilm(Integer id){
        return localFilms.get(id);
    }

    public FilmModel getFilm(Integer id){
        return films.get(id);
    }

    public List<FilmModel> getFilms() {
        return new ArrayList<>(films.values());
    }

    public boolean isFilmHere(FilmModel film){
        return films.containsKey(film.kinopoiskId);
    }

}
