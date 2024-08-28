package com.example.first.domain.usecase.outputUsecase;

import android.annotation.SuppressLint;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.IDbQueries;
import com.example.first.domain.models.LongFilmModel;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.dbUsecase.GetFilmByIdFromDb;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import io.reactivex.rxjava3.functions.Consumer;

public class GetLongFilmInformationById {
    public final GetFilmByIdFromDb getFilmByIdFromDb;

    public GetLongFilmInformationById(GetFilmByIdFromDb getFilmByIdFromDb){
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
                                    model.posterUrl
                            ));
                        }
                );
        return lfilm;
    }
}
