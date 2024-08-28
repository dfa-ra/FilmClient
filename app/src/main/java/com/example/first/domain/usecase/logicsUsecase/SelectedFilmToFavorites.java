package com.example.first.domain.usecase.logicsUsecase;

import com.example.first.domain.interfaces.IDbQueries;
import com.example.first.domain.interfaces.ILocalDB;
import com.example.first.domain.models.LongFilmModel;
import com.example.first.domain.usecase.dbUsecase.SetFilmToDb;

public class SelectedFilmToFavorites {
    private final SetFilmToDb setFilmToDb;
    public final ILocalDB localDB;

    public SelectedFilmToFavorites(SetFilmToDb setFilmToDb, ILocalDB localDB){
        this.setFilmToDb = setFilmToDb;
        this.localDB = localDB;
    }

    public void execute(Integer id){
        setFilmToDb.execute(localDB.getFilmById(id));
    }
}
