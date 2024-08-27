package com.example.first.data.dbqueries;

import android.content.Context;
import android.util.Log;

import com.example.first.data.dbqueries.dbroom.MainDB;
import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.IDbQueries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DbQueries implements IDbQueries {
    private static String Tag = "DbQueries";

    private final HashMap<Integer, FilmModel> localFilms = new HashMap<>();
    private final HashMap<Integer, FilmModel> dbfilms = new HashMap<>();
    private final MainDB db;
    private Executor executor = Executors.newSingleThreadExecutor();



    public DbQueries(Context context){
        db = MainDB.getInstance(context);
        init();
    }

    private void init(){
        executor.execute(() -> {
            // Выполнение запроса к базе данных в фоновом потоке
            List<FilmModel> dbfilms = db.getAppDatabase().filmDao().getAllFilmDetails();
            if (dbfilms != null)
                for (FilmModel film : dbfilms){
                    this.dbfilms.put(film.kinopoiskId, film);
                }
        });
    }

    @Override
    public void clearLocalBd() {
        localFilms.clear();
    }

    @Override
    public void selectById(Integer id){
        dbfilms.put(id, localFilms.get(id));
        new Thread(() -> db.getAppDatabase().filmDao().insertFilm(localFilms.get(id))).start();
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
        return dbfilms.get(id);
    }

    @Override
    public List<FilmModel> getFilms() {
        return new ArrayList<>(dbfilms.values());
    }

    @Override
    public boolean isFilmHere(FilmModel film){
        return dbfilms.containsKey(film.kinopoiskId);
    }

    @Override
    public void deleteFilmById(int id) {
        dbfilms.remove(id);
        new Thread(() -> db.getAppDatabase().filmDao().deleteById(id)).start();
    }

}
