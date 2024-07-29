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
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;

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
        int id = 300;
        httpQueries.getFilmById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FilmModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Может быть полезно сохранить Disposable, чтобы можно было отменить запрос при необходимости
                    }

                    @Override
                    public void onNext(FilmModel filmModel) {
                        // Обработка результата
                        films.add(filmModel);
                        Log.d("SomeClassTag", filmModel.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Обработка ошибки
                        Log.e("SomeClassTag", "Error fetching film: ", e);
                    }

                    @Override
                    public void onComplete() {
                        // Запрос завершён, вызываем метод для дальнейшей обработки данных
                        if (callback != null) {
                            callback.onDataFetched();
                        }
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
