package com.example.first.data.httpqueries;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.first.data.models.FilmModel;
import com.example.first.data.models.KeywordCollectionModel;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;

public class HttpQueries{

    private final static String Tag = "HttpQueriesTag";
    private final static String BaseURL = "https://kinopoiskapiunofficial.tech/";
    private Retrofit retrofit = RetrofitClient.getClient(BaseURL);
    private RequestFilm requestFilm = retrofit.create(RequestFilm.class);

    @SuppressLint("CheckResult")
    @NonNull
    public Observable<FilmModel> getFilmById(int id){
        return requestFilm.getFilmById(id)
                .onErrorResumeNext(throwable -> {
                    Log.e(Tag, "Error in requestFilm.getFilmById: ", throwable);
                    return Observable.empty(); // или Observable.error(throwable)
                });
    }


    public Observable<KeywordCollectionModel> getFilmByName(String name, Integer page) {
        return requestFilm.getFilmByName(name, page);
    }
}
