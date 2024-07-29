package com.example.first.data;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.first.data.httpqueries.HttpQueries;
import com.example.first.data.models.FilmModel;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.repository.FilmsRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FilmRepositoryImpl implements FilmsRepository {

    private List<FilmModel> films = new ArrayList<>();
    private HttpQueries httpQueries;
    private DataFetchCallback callback;

    public FilmRepositoryImpl(DataFetchCallback callback){
        httpQueries = new HttpQueries();
        this.callback = callback;
        loadPage();
    }

    @SuppressLint("CheckResult")
    private void loadPage(){
        Observable.range(1, 20) // Генерирует последовательность чисел от 1 до 20
                .flatMap(id -> httpQueries.getFilmById(300 + id)
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
                        films = filmModels;

                        // Запрос завершён, вызываем метод для дальнейшей обработки данных
                        if (callback != null) {
                            callback.onDataFetched();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Обработка ошибки
                        Log.e("SomeClassTag", "Error fetching films: ", e);
                    }
                });
    }

    @Override
    public List<ShortFilmModel> getAllShortFilmsInformation() {
        List<ShortFilmModel> returnedList = new ArrayList<>();

        for (FilmModel model: films){
            returnedList.add(new ShortFilmModel(
                    model.kinopoiskId,
                    model.nameRu,
                    model.ratingKinopoisk,
                    model.ratingImdb,
                    model.genres.get(0).genre,
                    model.isChecked));
        }
        return returnedList;
    }

    @Override
    public ShortFilmModel getShortFilmInformationById(int id) {
        return new ShortFilmModel(
                films.get(id).kinopoiskId,
                films.get(id).nameRu,
                films.get(id).ratingKinopoisk,
                films.get(id).ratingImdb,
                films.get(id).genres.get(0).genre,
                films.get(id).isChecked);
    }

    @Override
    public FilmModel getFilmInformationById(int id) {
        return null;
    }
}
