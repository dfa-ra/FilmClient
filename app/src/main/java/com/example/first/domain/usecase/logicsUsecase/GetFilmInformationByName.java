package com.example.first.domain.usecase.logicsUsecase;

import android.annotation.SuppressLint;

import com.example.first.data.dbqueries.DbQueries;
import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.IDbQueries;
import com.example.first.domain.interfaces.IRetrofit;

import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetFilmInformationByName {

    private final IRetrofit requestFilm;
    private final IDbQueries dbQueries;
    private final GetFilmInformationById getFilmInformationById;

    public GetFilmInformationByName(IRetrofit requestFilm, GetFilmInformationById getFilmInformationById, IDbQueries dbQueries){
        this.requestFilm = requestFilm;
        this.getFilmInformationById = getFilmInformationById;
        this.dbQueries = dbQueries;
    }

    @SuppressLint("CheckResult")
    public Single<List<FilmModel>> execute(String name, Integer page) {
        dbQueries.clearLocalBd();
        return requestFilm.getApi().getFilmByName(name, page)
            .subscribeOn(Schedulers.io())  // Выполняем запрос в фоновом потоке
            .observeOn(AndroidSchedulers.mainThread())  // Получаем результат в главном потоке
            .flatMapSingle(keywordCollectionModel -> {
                // Обрабатываем каждый фильм в коллекции и собираем результаты в список
                if (keywordCollectionModel.isSuccessful()) {
                    return Observable.fromIterable(keywordCollectionModel.body().films)
                            .concatMapSingle(filmModel -> {
                                        Single<FilmModel> modelSingle = getFilmInformationById.execute(filmModel.filmId);
                                        dbQueries.addNewFilm(modelSingle.blockingGet());
                                        return modelSingle;
                                    }
                            ).toList();
                }
                else {
                    return Single.error(new Throwable("Ошибка: " + keywordCollectionModel.code() + " " + keywordCollectionModel.message()));
                }
            }).last(Collections.emptyList());
    }
}
