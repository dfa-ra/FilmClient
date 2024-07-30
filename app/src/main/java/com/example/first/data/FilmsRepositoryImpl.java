package com.example.first.data;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.MonthDisplayHelper;

import com.example.first.data.httpqueries.HttpQueries;
import com.example.first.data.models.FilmModel;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.repository.FilmsRepository;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FilmsRepositoryImpl implements FilmsRepository {

    private HashMap<Integer, FilmModel> films = new HashMap<>();
    private HashMap<Integer, FilmModel> selectedFilms = new HashMap<>();
    private HttpQueries httpQueries;
    private DataFetchCallback callback;

    private static final String Tag = "FilmsRepositoryImplTag";

    public FilmsRepositoryImpl(DataFetchCallback callback){
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
                        for (FilmModel filmModel: filmModels){
                            films.put(filmModel.getKinopoiskId(), filmModel);
                        };

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

        for (FilmModel model: films.values()){
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
    public void selectedFilmToFavoritesUseCase(int id) {
        selectedFilms.put(id, films.get(id));
    }

    @Override
    public ShortFilmModel getShortFilmInformationById(int id) {
        FilmModel filmModel = films.get(id);
        assert filmModel != null;

        return new ShortFilmModel(
                filmModel.kinopoiskId,
                filmModel.nameRu,
                filmModel.ratingKinopoisk,
                filmModel.ratingImdb,
                filmModel.genres.get(0).genre,
                filmModel.isChecked);
    }

    @Override
    public FilmModel getFilmInformationById(int id) {
        return null;
    }
}
