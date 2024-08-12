package com.example.first.domain.usecase.outputUsecase;

import android.util.Log;

import com.example.first.data.dbqueries.DbQueries;
import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.IDbQueries;
import com.example.first.domain.models.LongFilmModel;

public class GetLongFilmInformationById {
    private final IDbQueries dbQueries;

    public GetLongFilmInformationById(IDbQueries dbQueries){
        this.dbQueries = dbQueries;
    }

    public LongFilmModel execute(int id){
        FilmModel model = dbQueries.getLocalFilm(id);
        return new LongFilmModel(
                model.kinopoiskId,
                model.nameRu,
                model.description,
                model.countries,
                model.genres,
                model.ratingKinopoisk,
                model.ratingImdb,
                model.posterUrl
        );
    }
}
