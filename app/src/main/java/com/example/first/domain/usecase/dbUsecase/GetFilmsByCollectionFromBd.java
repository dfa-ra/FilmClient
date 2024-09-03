package com.example.first.domain.usecase.dbUsecase;

import android.annotation.SuppressLint;

import com.example.first.data.common.enums.CollectionType;
import com.example.first.domain.interfaces.IDbQueries;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.logicsUsecase.AllToShortFilmsInformation;

import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetFilmsByCollectionFromBd {
    private final IDbQueries db;
    public final AllToShortFilmsInformation allToShortFilmsInformation;

    public GetFilmsByCollectionFromBd(IDbQueries db, AllToShortFilmsInformation allToShortFilmsInformation){
        this.db = db;
        this.allToShortFilmsInformation = allToShortFilmsInformation;
    }

    @SuppressLint("CheckResult")
    public Flowable<List<ShortFilmModel>> execute(CollectionType type) {
        return db.getAppDatabase().dao().getFilmsByCollection(type.getNameCollections())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(model -> {
                    List<ShortFilmModel> execute = allToShortFilmsInformation.execute(model);
                    return Flowable.just(execute);
                });
    }
}
