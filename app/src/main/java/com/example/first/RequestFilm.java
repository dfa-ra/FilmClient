package com.example.first;

import com.example.first.filmStrip.FilmItem;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.Getter;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestFilm {
    @Headers({
            "X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b",
            "Content-Type: application/json"
    })
    @GET("/api/v2.2/films/{id}")
    Observable<FilmItem> getFilmById(@Path("id") Integer id);

    @Headers({
            "X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b",
            "Content-Type: application/json"
    })
    @GET("api/v2.2/films/search-by-keyword")
    Observable<FilmItem> getFilmByName(@Query("keyword") String name, @Query("keyword") Integer page);
}

