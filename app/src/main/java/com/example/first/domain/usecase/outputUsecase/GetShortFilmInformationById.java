package com.example.first.domain.usecase.outputUsecase;

import com.example.first.data.dbqueries.DBLocal;
import com.example.first.data.models.FilmModel;
import com.example.first.domain.models.ShortFilmModel;

public class GetShortFilmInformationById {

    public ShortFilmModel execute(int id) {
        FilmModel filmModel = DBLocal.getInstance().getFilm(id);
        assert filmModel != null;

        return new ShortFilmModel(
                filmModel.kinopoiskId,
                filmModel.nameRu,
                filmModel.ratingKinopoisk,
                filmModel.ratingImdb,
                filmModel.genres.get(0).genre,
                filmModel.isChecked);
    }

}
