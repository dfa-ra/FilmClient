package com.example.first.data.httpqueries;

import com.example.first.data.models.FilmModel;

import io.reactivex.rxjava3.core.Observable;
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
    Observable<FilmModel> getFilmById(@Path("id") Integer id);

    @Headers({
            "X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b",
            "Content-Type: application/json"
    })
    @GET("api/v2.2/films/search-by-keyword")
    Observable<FilmModel> getFilmByName(@Query("keyword") String name, @Query("keyword") Integer page);
}
