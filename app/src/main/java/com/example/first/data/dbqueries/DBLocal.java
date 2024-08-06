package com.example.first.data.dbqueries;

import com.example.first.data.models.FilmModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBLocal {

    private static DBLocal dbLocal = null;

    private final HashMap<Integer, FilmModel> films = new HashMap<>();

    public static DBLocal getInstance(){
        if (dbLocal == null) dbLocal = new DBLocal();
        return dbLocal;
    }

    private DBLocal(){}

    public void addNewFilm(FilmModel film){
        films.put(film.getKinopoiskId(), film);
    }

    public void clearBd(){
        films.clear();
    }

    public FilmModel getFilm(Integer id){
        return films.get(id);
    }

    public List<FilmModel> getFilms() {
        return new ArrayList<>(films.values());
    }

}
