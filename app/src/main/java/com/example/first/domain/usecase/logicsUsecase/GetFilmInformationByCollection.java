package com.example.first.domain.usecase.logicsUsecase;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.first.data.dbqueries.DBLocal;
import com.example.first.domain.interfaces.DataFetchCallback;
import com.example.first.domain.interfaces.Requests;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetFilmInformationByCollection {
    private final Requests requestFilm;
    private final DataFetchCallback callback;
    private final GetFilmInformationById getFilmInformationById;

    public GetFilmInformationByCollection(Requests requestFilm, GetFilmInformationById getFilmInformationById, DataFetchCallback callback) {
        this.requestFilm = requestFilm;
        this.callback = callback;
        this.getFilmInformationById = getFilmInformationById;
    }

    @SuppressLint("CheckResult")
    public void execute(String type, Integer page) {
        Log.i("msRepositoryImplTag", "Начало выполнения главного конвейера");

        requestFilm.getFilmByCollection(type, page)
                .subscribeOn(Schedulers.io()) // Выполняем запрос в фоновом потоке
                .observeOn(AndroidSchedulers.mainThread()) // Получаем результат в главном потоке
                .subscribe(
                        collectionModel -> {
                            Log.i("msRepositoryImplTag", "Запрос коллекции выполнен");
                            DBLocal.getInstance().clearBd();

                            Observable.fromIterable(collectionModel.items)
                                    .flatMapCompletable(filmModel -> {
                                        Log.i("msRepositoryImplTag", "Вызываем поиск по id для filmId: " + filmModel.getKinopoiskId());
                                        return Completable.fromAction(() -> getFilmInformationById.execute(filmModel.getKinopoiskId()))
                                                .subscribeOn(Schedulers.io());
                                    })
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                            () -> {
                                                Log.i("msRepositoryImplTag", "Обработка завершена");
                                                if (callback != null) {
                                                    callback.onDataFetched();
                                                    Log.d("msRepositoryImplTag", "Data fetched and stored in local DB");
                                                }
                                            },
                                            throwable -> {
                                                Log.e("msRepositoryImplTag", "Ошибка при обработке фильмов: ", throwable);
                                            }
                                    );
                        },
                        throwable -> {
                            Log.e("msRepositoryImplTag", "Ошибка при запросе коллекции: ", throwable);
                        },
                        () -> {
                            Log.i("msRepositoryImplTag", "Запрос коллекции завершён");
                        }
                );
    }
}
