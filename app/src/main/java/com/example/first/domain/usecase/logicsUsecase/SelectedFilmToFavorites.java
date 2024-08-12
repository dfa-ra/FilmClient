package com.example.first.domain.usecase.logicsUsecase;

import com.example.first.data.dbqueries.DbQueries;
import com.example.first.domain.interfaces.IDbQueries;

public class SelectedFilmToFavorites {
    private final IDbQueries dbQueries;

    public SelectedFilmToFavorites(IDbQueries dbQueries){
        this.dbQueries = dbQueries;
    }

    public void execute(Integer id){
        dbQueries.selectById(id);
    }
}
