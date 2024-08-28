package com.example.first.domain.usecase.outputUsecase;

import android.annotation.SuppressLint;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.dbUsecase.GetFilmsFromDb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetShortInformationAboutFilmsDb {

    private final GetFilmsFromDb getFilmsFromDb;

    public GetShortInformationAboutFilmsDb(GetFilmsFromDb getFilmsFromDb){
        this.getFilmsFromDb = getFilmsFromDb;
    }

    @SuppressLint("CheckResult")
    public CompletableFuture<List<ShortFilmModel>> execute(){
        CompletableFuture<List<ShortFilmModel>> shfilms = new CompletableFuture<>();
        getFilmsFromDb.execute()
                .subscribe(models -> {
                    List<ShortFilmModel> shmodels = new ArrayList<>();
                    for (FilmModel model: models){
                        shmodels.add(new ShortFilmModel(
                                model.kinopoiskId,
                                model.nameRu,
                                model.ratingKinopoisk,
                                model.ratingImdb,
                                model.genres.get(0).genre,
                                model.posterUrlPreview,
                                model.isChecked,
                                model.posterPreview));
                    }
                    shfilms.complete(shmodels);
                });
        return shfilms;
    }
}

