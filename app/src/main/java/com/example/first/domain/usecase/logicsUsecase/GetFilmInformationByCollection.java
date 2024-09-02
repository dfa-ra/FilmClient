package com.example.first.domain.usecase.logicsUsecase;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.ILocalDB;
import com.example.first.domain.interfaces.IRetrofit;
import com.example.first.domain.models.ShortFilmModel;

import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetFilmInformationByCollection {
    private final IRetrofit requestFilm;
    private final ILocalDB localDB;
    private final GetFilmInformationById getFilmInformationById;
    private final FilmModelToShortModel filmModelToShortModel;

    public GetFilmInformationByCollection(IRetrofit requestFilm, GetFilmInformationById getFilmInformationById, FilmModelToShortModel filmModelToShortModel, ILocalDB localDB) {
        this.requestFilm = requestFilm;
        this.getFilmInformationById = getFilmInformationById;
        this.localDB = localDB;
        this.filmModelToShortModel = filmModelToShortModel;
    }

    public Single<List<ShortFilmModel>> execute(String type, Integer page) {
        localDB.clearLocalBd();
        return requestFilm.getApi().getFilmByCollection(type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMapSingle(keywordCollectionModel -> {
                    if (keywordCollectionModel.isSuccessful()) {
                        return Observable.fromIterable(keywordCollectionModel.body().items)
                                .concatMapSingle(filmModel -> {
                                            Single<FilmModel> modelSingle = getFilmInformationById.execute(filmModel.kinopoiskId);
                                            localDB.addFilm(modelSingle.blockingGet());
                                            return Single.just(filmModelToShortModel.execute(modelSingle.blockingGet()));
                                        }
                                )
                                .subscribeOn(Schedulers.io())
                                .toList();
                    } else {
                        return Single.error(new Throwable("Ошибка: " + keywordCollectionModel.code() + " " + keywordCollectionModel.message()));
                    }
                }
                ).last(Collections.emptyList());
    }
}
