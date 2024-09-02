package com.example.first.domain.usecase.dbUsecase;

import android.annotation.SuppressLint;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.IDbQueries;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetFilmsFromDb {
    private final IDbQueries db;

    public GetFilmsFromDb(IDbQueries db){
        this.db = db;
    }

    @SuppressLint("CheckResult")
    public Flowable<List<FilmModel>> execute(){
        return db.getAppDatabase().dao().getAllFilmDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
