package com.example.first.data.dbqueries;

import android.util.Log;

import com.example.first.data.models.FilmModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbQueries {
    private static String Tag = "DbQueries";
    private static DbQueries dbQueries = null;

    private final HashMap<Integer, FilmModel> films = new HashMap<>();

    public static DbQueries getInstance(){
        if (dbQueries == null) dbQueries = new DbQueries();
        return dbQueries;
    }

    private DbQueries(){}

    public void addNewFilm(FilmModel film){
        Log.i(Tag, "add new item");
        films.put(film.getKinopoiskId(), film);
    }

    public FilmModel getFilm(Integer id){
        return films.get(id);
    }

    public List<FilmModel> getFilms() {
        return new ArrayList<>(films.values());
    }

    public boolean isFilmHere(FilmModel film){
        Log.i(Tag, films.toString());
        Log.i(Tag, String.valueOf(film.kinopoiskId));
        Log.i(Tag, film.nameRu + " " + String.valueOf(films.containsKey(film.kinopoiskId)));
        return films.containsKey(film.kinopoiskId);
    }

}
