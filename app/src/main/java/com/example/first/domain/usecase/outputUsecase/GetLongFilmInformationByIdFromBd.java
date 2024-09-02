package com.example.first.domain.usecase.outputUsecase;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.room.rxjava3.EmptyResultSetException;

import com.example.first.domain.models.LongFilmModel;
import com.example.first.domain.usecase.dbUsecase.GetFilmByIdFromDb;

import java.util.concurrent.CompletableFuture;

public class GetLongFilmInformationByIdFromBd {
    public final GetFilmByIdFromDb getFilmByIdFromDb;

    public GetLongFilmInformationByIdFromBd(GetFilmByIdFromDb getFilmByIdFromDb){
        this.getFilmByIdFromDb = getFilmByIdFromDb;
    }

    @SuppressLint("CheckResult")
    public CompletableFuture<LongFilmModel> execute(int id){
        CompletableFuture<LongFilmModel> lfilm = new CompletableFuture<>();

        getFilmByIdFromDb.execute(id)
                .subscribe(model -> {
                            lfilm.complete(new LongFilmModel(
                                    model.kinopoiskId,
                                    model.nameRu,
                                    model.description,
                                    model.countries,
                                    model.genres,
                                    model.ratingKinopoisk,
                                    model.ratingImdb,
                                    model.posterUrl,
                                    model.comment,
                                    model.poster
                            ));
                        }, throwable -> {
                            if (throwable instanceof EmptyResultSetException) {
                                lfilm.completeExceptionally(throwable); // Пробрасываем ошибку пустого результата
                            } else {
                                Log.e("aa99", throwable.getMessage());
                                // Обработка другой ошибки
                            }
                        }
                );
        return lfilm;
    }
}
