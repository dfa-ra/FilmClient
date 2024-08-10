package com.example.first.domain.usecase.outputUsecase;

import com.example.first.data.models.FilmModel;
import com.example.first.domain.models.ShortFilmModel;

import java.util.ArrayList;
import java.util.List;

public class AllToShortFilmsInformation {

    public List<ShortFilmModel> execute(List<FilmModel> films){
        List<ShortFilmModel> returnedList = new ArrayList<>();

        for (FilmModel model: films){
            if (model.genres.isEmpty()){
                returnedList.add(new ShortFilmModel(
                        model.kinopoiskId,
                        model.nameRu,
                        model.ratingKinopoisk,
                        model.ratingImdb,
                        "-",
                        model.isChecked));
            } else {
                returnedList.add(new ShortFilmModel(
                        model.kinopoiskId,
                        model.nameRu,
                        model.ratingKinopoisk,
                        model.ratingImdb,
                        model.genres.get(0).genre,
                        model.isChecked));
            }
        }
        return returnedList;
    }
}
