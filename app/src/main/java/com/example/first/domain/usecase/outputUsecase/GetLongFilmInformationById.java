package com.example.first.domain.usecase.outputUsecase;

import com.example.first.data.dbqueries.DbQueries;
import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.models.LongFilmModel;

public class GetLongFilmInformationById {
    public LongFilmModel execute(int id){
        FilmModel model = DbQueries.getInstance().getLocalFilm(id);

        return new LongFilmModel(
                model.kinopoiskId,
                model.nameRu,
                model.description,
                model.countries,
                model.genres,
                model.ratingKinopoisk,
                model.ratingImdb,
                model.posterUrl,
                model.posterUrlPreview
        );
    }
}
