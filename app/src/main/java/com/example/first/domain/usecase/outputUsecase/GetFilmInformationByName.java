package com.example.first.domain.usecase.outputUsecase;

import android.annotation.SuppressLint;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.ILocalDB;
import com.example.first.domain.interfaces.IRetrofit;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.logicsUsecase.FilmModelToShortModel;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationById;

import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetFilmInformationByName {

    private final IRetrofit requestFilm;
    private final ILocalDB localDB;
    private final GetFilmInformationById getFilmInformationById;
    private final FilmModelToShortModel filmModelToShortModel;

    public GetFilmInformationByName(IRetrofit requestFilm, GetFilmInformationById getFilmInformationById, FilmModelToShortModel filmModelToShortModel ,ILocalDB localDB){
        this.requestFilm = requestFilm;
        this.getFilmInformationById = getFilmInformationById;
        this.localDB = localDB;
        this.filmModelToShortModel = filmModelToShortModel;
    }

    @SuppressLint("CheckResult")
    public Single<List<ShortFilmModel>> execute(String name, Integer page) {
        localDB.clearLocalBd();
        return requestFilm.getApi().getFilmByName(name, page)
            .subscribeOn(Schedulers.io())  // Выполняем запрос в фоновом потоке
            .observeOn(AndroidSchedulers.mainThread())  // Получаем результат в главном потоке
            .flatMapSingle(keywordCollectionModel -> {
                // Обрабатываем каждый фильм в коллекции и собираем результаты в список
                if (keywordCollectionModel.isSuccessful()) {
                    return Observable.fromIterable(keywordCollectionModel.body().films)
                            .concatMapSingle(filmModel -> {
                                        Single<FilmModel> modelSingle = getFilmInformationById.execute(filmModel.filmId);
                                        localDB.addFilm(modelSingle.blockingGet());
                                        return Single.just(filmModelToShortModel.execute(modelSingle.blockingGet()));
                                    }
                            ).toList();
                }
                else {
                    return Single.error(new Throwable("Ошибка: " + keywordCollectionModel.code() + " " + keywordCollectionModel.message()));
                }
            }).last(Collections.emptyList());
    }
}
