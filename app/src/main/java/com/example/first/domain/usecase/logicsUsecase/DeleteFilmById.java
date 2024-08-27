package com.example.first.domain.usecase.logicsUsecase;

import com.example.first.domain.interfaces.IDbQueries;

public class DeleteFilmById {
    private final IDbQueries dbQueries;

    public DeleteFilmById(IDbQueries dbQueries){
        this.dbQueries = dbQueries;
    }

    public void execute(Integer id){
        dbQueries.deleteFilmById(id);
    }
}
