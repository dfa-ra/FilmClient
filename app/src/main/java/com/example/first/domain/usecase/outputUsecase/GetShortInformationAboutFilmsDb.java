package com.example.first.domain.usecase.outputUsecase;

import com.example.first.data.dbqueries.DbQueries;
import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.IDbQueries;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;

import java.util.ArrayList;
import java.util.List;

public class GetShortInformationAboutFilmsDb {

    private final IDbQueries dbQueries;

    public GetShortInformationAboutFilmsDb(IDbQueries dbQueries){
        this.dbQueries = dbQueries;
    }

    public List<ShortFilmModel> execute(){
        List<ShortFilmModel> returnedList = new ArrayList<>();

        for (FilmModel model: dbQueries.getFilms()){
            returnedList.add(new ShortFilmModel(
                    model.kinopoiskId,
                    model.nameRu,
                    model.ratingKinopoisk,
                    model.ratingImdb,
                    model.genres.get(0).genre,
                    model.posterUrlPreview,
                    model.isChecked = true,
                    model.posterPreview));
        }
        return returnedList;
    }
}

