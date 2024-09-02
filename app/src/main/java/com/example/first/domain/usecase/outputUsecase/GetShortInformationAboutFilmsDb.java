package com.example.first.domain.usecase.outputUsecase;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.dbUsecase.GetFilmsFromDb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;

public class GetShortInformationAboutFilmsDb {

    private final GetFilmsFromDb getFilmsFromDb;

    public GetShortInformationAboutFilmsDb(GetFilmsFromDb getFilmsFromDb){
        this.getFilmsFromDb = getFilmsFromDb;
    }

    @SuppressLint("CheckResult")
    public Flowable<List<ShortFilmModel>> execute(){

        Log.d("aa66","GetShortInformationAboutFilmsDb");
        return getFilmsFromDb.execute()
                .map(longModel -> {
                    List<ShortFilmModel> shmodels = new ArrayList<>();
                    for (FilmModel model: longModel){
                        shmodels.add(new ShortFilmModel(
                                model.kinopoiskId,
                                model.nameRu,
                                model.ratingKinopoisk,
                                model.ratingImdb,
                                model.genres.get(0).genre,
                                model.posterUrlPreview,
                                model.isChecked,
                                model.isReadable,
                                model.posterPreview));
                    }
                    return shmodels;
                });
    }
}

