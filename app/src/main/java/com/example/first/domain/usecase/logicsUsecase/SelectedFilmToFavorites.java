package com.example.first.domain.usecase.logicsUsecase;

import com.example.first.data.dbqueries.DbQueries;

public class SelectedFilmToFavorites {
    public void execute(Integer id){
        DbQueries.getInstance().selectById(id);
    }
}
