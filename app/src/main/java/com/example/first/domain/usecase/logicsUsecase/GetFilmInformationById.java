package com.example.first.domain.usecase.logicsUsecase;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.first.data.httpqueries.IRetrofit;
import com.example.first.data.models.FilmModel;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetFilmInformationById {

    private final IRetrofit requestFilm;

    public GetFilmInformationById(IRetrofit requestFilm){
        this.requestFilm = requestFilm;
    }

    @SuppressLint("CheckResult")
    public Single<FilmModel> execute(int id) {
        return requestFilm.getFilmById(id)
                .singleOrError()
                .doOnError(e -> Log.e("aa99", "Ошибка: " + e.getMessage()))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }
}
