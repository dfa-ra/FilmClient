package com.example.first.domain.usecase.dbUsecase;

import android.annotation.SuppressLint;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.IDbQueries;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetFilmByIdFromDb {
    private final IDbQueries db;

    public GetFilmByIdFromDb(IDbQueries db){
        this.db = db;
    }

    @SuppressLint("CheckResult")
    public Single<FilmModel> execute(int id){
        return db.getAppDatabase().dao().getFilmsById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
