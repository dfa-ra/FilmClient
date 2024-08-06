package com.example.first.domain.usecase.logicsUsecase;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.first.data.dbqueries.DBLocal;
import com.example.first.data.httpqueries.HttpQueries;
import com.example.first.data.httpqueries.RequestFilm;
import com.example.first.domain.interfaces.DataFetchCallback;
import com.example.first.domain.interfaces.Requests;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetFilmsInformationByName {

    private final Requests requestFilm;
    private final DataFetchCallback callback;
    private final GetFilmInformationById getFilmInformationById;

    public GetFilmsInformationByName(Requests requestFilm, GetFilmInformationById getFilmInformationById, DataFetchCallback callback){
        this.requestFilm = requestFilm;
        this.callback = callback;
        this.getFilmInformationById = getFilmInformationById;
    }

    @SuppressLint("CheckResult")
    public void execute(String name, Integer page){
        requestFilm.getFilmByName(name, page).subscribeOn(Schedulers.io()) // Выполняем запрос в фоновом потоке
            .observeOn(AndroidSchedulers.mainThread()) // Получаем результат в главном потоке
            .subscribe(
                keywordCollectionModel -> {

                    DBLocal.getInstance().clearBd();

                    Observable.fromIterable(keywordCollectionModel.films)
                        .doOnNext(filmModel -> getFilmInformationById.execute(filmModel.filmId))
                        .subscribe(
                            filmModel -> {
                                // Дополнительная обработка каждого элемента, если необходимо
                            },
                            throwable -> {
                                // Обработка ошибок в процессе обработки фильмов
                            },
                            () -> {
                                // Завершение обработки
                                // Запрос завершён, вызываем метод для дальнейшей обработки данных
                                if (callback != null) {
                                    callback.onDataFetched();
                                }
                            }
                        );
                },
                throwable -> {
                    // Обработка ошибок при запросе
                }
            );
    }

}
