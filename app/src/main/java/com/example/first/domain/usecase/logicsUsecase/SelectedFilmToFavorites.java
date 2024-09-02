package com.example.first.domain.usecase.logicsUsecase;

import android.util.Log;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.ILocalDB;
import com.example.first.domain.usecase.dbUsecase.SetFilmToDb;
import com.example.first.domain.usecase.outputUsecase.GetFilmPoster;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SelectedFilmToFavorites {
    private final SetFilmToDb setFilmToDb;
    private final ILocalDB localDB;
    private final GetFilmPoster getFilmPoster;
    private final Executor executor = Executors.newSingleThreadExecutor();



    public SelectedFilmToFavorites(SetFilmToDb setFilmToDb, ILocalDB localDB, GetFilmPoster getFilmPoster){
        this.setFilmToDb = setFilmToDb;
        this.localDB = localDB;
        this.getFilmPoster = getFilmPoster;
    }

    public void execute(Integer id){

        Log.d("aa66", "SelectedFilmToFavorites");
        executor.execute(() -> {
            FilmModel model = localDB.getFilmById(id);
            Log.d("aa66",model.posterUrlPreview);
            Log.d("aa66",model.posterUrl);
            model.isChecked = true;
            model.poster = getFilmPoster.execute(model.posterUrl);
            Log.d("aa66", model.toString());
            setFilmToDb.execute(model);
        });
    }
}
