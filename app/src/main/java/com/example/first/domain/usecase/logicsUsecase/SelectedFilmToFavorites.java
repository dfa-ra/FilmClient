package com.example.first.domain.usecase.logicsUsecase;

import com.example.first.data.dbqueries.DBLocal;
import com.example.first.data.dbqueries.DbQueries;

public class SelectedFilmToFavorites {

    public void execute(int id){
        DbQueries.getInstance().addNewFilm(DBLocal.getInstance().getFilm(id));
    }
}
