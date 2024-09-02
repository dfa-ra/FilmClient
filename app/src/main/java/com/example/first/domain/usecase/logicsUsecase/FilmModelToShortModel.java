package com.example.first.domain.usecase.logicsUsecase;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.models.ShortFilmModel;

public class FilmModelToShortModel {
    public ShortFilmModel execute(FilmModel model){
        return new ShortFilmModel(
                model.kinopoiskId,
                model.nameRu,
                model.ratingKinopoisk,
                model.ratingImdb,
                model.genres.get(0).genre,
                model.posterUrlPreview,
                model.isChecked,
                model.isReadable,
                model.posterPreview);
    }
}
