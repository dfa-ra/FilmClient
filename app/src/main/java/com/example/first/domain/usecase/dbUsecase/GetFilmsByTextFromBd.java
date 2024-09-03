package com.example.first.domain.usecase.dbUsecase;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.IDbQueries;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.logicsUsecase.AllToShortFilmsInformation;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetFilmsByTextFromBd {
    private final IDbQueries db;
    public final AllToShortFilmsInformation allToShortFilmsInformation;

    public GetFilmsByTextFromBd(IDbQueries db, AllToShortFilmsInformation allToShortFilmsInformation){
        this.db = db;
        this.allToShortFilmsInformation = allToShortFilmsInformation;
    }

    @SuppressLint("CheckResult")
    public Flowable<List<ShortFilmModel>> execute(String text){
        Log.d("aa22", "executeeeeee");
        return db.getAppDatabase().dao().getFilmsByText(text)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(model -> {
                    List<ShortFilmModel> execute = allToShortFilmsInformation.execute(model);
                    Log.d("aa22", execute.toString());
                    return Flowable.just(execute);
                });
    }
}
