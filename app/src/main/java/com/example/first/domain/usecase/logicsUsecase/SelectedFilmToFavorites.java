package com.example.first.domain.usecase.logicsUsecase;

import com.example.first.data.dbqueries.DbQueries;
import com.example.first.data.models.FilmModel;

public class SelectedFilmToFavorites {
    public void execute(Integer id){
        DbQueries.getInstance().selectById(id);
    }
}
