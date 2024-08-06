package com.example.first.domain.interfaces;

import com.example.first.data.models.CollectionModel;
import com.example.first.data.models.FilmModel;
import com.example.first.data.models.KeywordCollectionModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Requests {
    Observable<FilmModel> getFilmById(int id);
    Observable<KeywordCollectionModel> getFilmByName(String name, Integer page);
    Observable<CollectionModel> getFilmByCollection(String type, Integer page);

}
