package com.example.first.domain.usecase.logicsUsecase;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.first.data.dbqueries.DBLocal;
import com.example.first.data.httpqueries.HttpQueries;
import com.example.first.data.httpqueries.RequestFilm;
import com.example.first.data.models.FilmModel;
import com.example.first.domain.interfaces.DataFetchCallback;
import com.example.first.domain.interfaces.Requests;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetFilmInformationById {

    private final Requests requestFilm;
    private final DataFetchCallback callback;

    public GetFilmInformationById(Requests requestFilm, DataFetchCallback callback){
        this.requestFilm = requestFilm;
        this.callback = callback;
    }

    @SuppressLint("CheckResult")
    public void execute(int id){
        Observable.just(id)
                // Генерирует последовательность чисел от 1 до 20
                .flatMap(number -> requestFilm.getFilmById(number)
                        .subscribeOn(Schedulers.io())) // Выполняем каждый запрос на IO Scheduler
                .toList() // Собираем результаты в список
                .observeOn(AndroidSchedulers.mainThread()) // Наблюдаем на главном потоке
                .subscribe(new SingleObserver<List<FilmModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Может быть полезно сохранить Disposable, чтобы можно было отменить запрос при необходимости
                    }

                    @Override
                    public void onSuccess(List<FilmModel> filmModels) {
                        // Обработка результата
                        for (FilmModel filmModel: filmModels){
                            DBLocal.getInstance().addNewFilm(filmModel);
                        };

                        // Запрос завершён, вызываем метод для дальнейшей обработки данных
                        if (callback != null) {
                            callback.onDataFetched();
                            Log.d("msRepositoryImplTag", "poos data fetch");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Обработка ошибки
                        Log.e("SomeClassTag", "Error fetching films: ", e);
                    }
                });
    }
}
