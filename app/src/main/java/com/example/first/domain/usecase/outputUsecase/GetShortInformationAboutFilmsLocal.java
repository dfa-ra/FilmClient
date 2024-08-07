package com.example.first.domain.usecase.outputUsecase;

import android.util.Log;

import com.example.first.data.dbqueries.DBLocal;
import com.example.first.data.models.FilmModel;
import com.example.first.domain.models.ShortFilmModel;

import java.util.ArrayList;
import java.util.List;

public class GetShortInformationAboutFilmsLocal {

    public List<ShortFilmModel> execute(){
        List<ShortFilmModel> returnedList = new ArrayList<>();

        Log.d("msRepositoryImplTag", "=====" + DBLocal.getInstance().getFilms().toString());
        for (FilmModel model: DBLocal.getInstance().getFilms()){
            returnedList.add(new ShortFilmModel(
                    model.kinopoiskId,
                    model.nameRu,
                    model.ratingKinopoisk,
                    model.ratingImdb,
                    model.genres.get(0).genre,
                    model.isChecked));
        }
        return returnedList;
    }
}
