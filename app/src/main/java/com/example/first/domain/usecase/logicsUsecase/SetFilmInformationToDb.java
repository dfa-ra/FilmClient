package com.example.first.domain.usecase.logicsUsecase;

import com.example.first.data.dbqueries.DBLocal;
import com.example.first.data.dbqueries.DbQueries;
import com.example.first.domain.models.ShortFilmModel;

public class SetFilmInformationToDb {
    public void execute(ShortFilmModel film){
        DbQueries.getInstance().addNewFilm(DBLocal.getInstance().getFilm(film.kinopoiskId));
    }
}
