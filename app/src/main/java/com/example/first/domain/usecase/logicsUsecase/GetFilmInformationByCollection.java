package com.example.first.domain.usecase.logicsUsecase;

import android.util.Log;

import com.example.first.data.dbqueries.DbQueries;
import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.IRetrofit;

import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetFilmInformationByCollection {
    private final IRetrofit requestFilm;
    private final GetFilmInformationById getFilmInformationById;

    public GetFilmInformationByCollection(IRetrofit requestFilm, GetFilmInformationById getFilmInformationById) {
        this.requestFilm = requestFilm;
        this.getFilmInformationById = getFilmInformationById;
    }

    public Single<List<FilmModel>> execute(String type, Integer page) {
        Log.i("aa99", "execute collection");
        return requestFilm.getApi().getFilmByCollection(type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMapSingle(keywordCollectionModel -> {
                    if (keywordCollectionModel.isSuccessful()) {
                        return Observable.fromIterable(keywordCollectionModel.body().items)
                                .concatMapSingle(filmModel -> {
                                            DbQueries.getInstance().addNewFilm(filmModel);
                                            return getFilmInformationById.execute(filmModel.kinopoiskId);
                                        }
                                ).toList();
                    } else {
                        return Single.error(new Throwable("Ошибка: " + keywordCollectionModel.code() + " " + keywordCollectionModel.message()));
                    }
                }
                ).last(Collections.emptyList());
    }
}
