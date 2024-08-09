package com.example.first.data.httpqueries;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.first.data.models.CollectionModel;
import com.example.first.data.models.FilmModel;
import com.example.first.data.models.KeywordCollectionModel;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;

public class IRetrofitImpl implements IRetrofit {

    private final static String Tag = "HttpQueriesTag";
    private final static String BaseURL = "https://kinopoiskapiunofficial.tech/";
    private Retrofit retrofit = RetrofitClient.getClient(BaseURL);
    private IRetrofit requestFilm = retrofit.create(IRetrofit.class);

    public IRetrofitImpl(){}

    @SuppressLint("CheckResult")
    @NonNull
    @Override
    public Observable<FilmModel> getFilmById(int id){
        return requestFilm.getFilmById(id)
                .onErrorResumeNext(throwable -> {
                    Log.e(Tag, "Error in requestFilm.getFilmById: ", throwable);
                    return Observable.empty(); // или Observable.error(throwable)
                });
    }

    @Override
    public Observable<KeywordCollectionModel> getFilmByName(String name, Integer page) {
        return requestFilm.getFilmByName(name, page);
    }

    @Override
    public Observable<CollectionModel> getFilmByCollection(String type, Integer page) {
        return requestFilm.getFilmByCollection(type, page);
    }
}