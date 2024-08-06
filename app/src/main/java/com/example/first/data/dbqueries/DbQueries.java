package com.example.first.data.dbqueries;

import com.example.first.data.models.FilmModel;
import com.example.first.domain.models.ShortFilmModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbQueries {

    private static DbQueries dbQueries = null;

    private final HashMap<Integer, FilmModel> films = new HashMap<>();

    public static DbQueries getInstance(){
        if (dbQueries == null) dbQueries = new DbQueries();
        return dbQueries;
    }

    private DbQueries(){}

    public void addNewFilm(FilmModel film){
        films.put(film.getKinopoiskId(), film);
    }

    public FilmModel getFilm(Integer id){
        return films.get(id);
    }

    public List<FilmModel> getFilms() {
        return new ArrayList<>(films.values());
    }

}
