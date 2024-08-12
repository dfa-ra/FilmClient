package com.example.first.data.httpqueries;

import com.example.first.data.models.CollectionModel;
import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.data.models.KeywordCollectionModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IRetrofit {
    @Headers({
            "X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b",
            "Content-Type: application/json"
    })
    @GET("/api/v2.2/films/{id}")
    Observable<Response<FilmModel>> getFilmById(@Path("id") int id);

    @Headers({
            "X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b",
            "Content-Type: application/json"
    })
    @GET("api/v2.1/films/search-by-keyword")
    Observable<Response<KeywordCollectionModel>> getFilmByName(@Query("keyword") String name, @Query("page") Integer page);

    @Headers({
            "X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b",
            "Content-Type: application/json"
    })
    @GET("api/v2.2/films/collections")
    Observable<Response<CollectionModel>> getFilmByCollection(@Query("type") String type, @Query("page") Integer page);
}

