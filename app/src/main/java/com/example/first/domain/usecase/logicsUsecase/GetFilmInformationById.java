package com.example.first.domain.usecase.logicsUsecase;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.IRetrofit;
import com.example.first.domain.usecase.outputUsecase.GetFilmPoster;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetFilmInformationById {

    private final IRetrofit requestFilm;
    private final GetFilmPoster getFilmPoster;

    public GetFilmInformationById(IRetrofit requestFilm, GetFilmPoster getFilmPoster){
        this.requestFilm = requestFilm;
        this.getFilmPoster = getFilmPoster;
    }

    @SuppressLint("CheckResult")
    public Single<FilmModel> execute(int id) {
        return requestFilm.getApi().getFilmById(id)
                .singleOrError()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(response -> {
                    if (response.isSuccessful()) {
                        // Если запрос успешен, извлекаем тело ответа (FilmModel)
                        FilmModel film = response.body();
                        assert film != null;
                        film.posterPreview = getFilmPoster.execute(film.posterUrlPreview);
                        return Single.just(film);  // Возвращаем успешный результат
                    } else {
                        // Если запрос не успешен, создаем ошибку с кодом и сообщением от сервера
                        return Single.error(new Throwable("Ошибка: " + response.code() + " " + response.message()));
                    }
                })
                .doOnError(e -> Log.e("aa99", "Ошибка: " + e.getMessage()));
    }
}
