package com.example.first.domain.usecase.dbUsecase;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.IDbQueries;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DeleteFilmByIdFromBd {
    private final IDbQueries queries;
    private final Executor executor = Executors.newSingleThreadExecutor();


    public DeleteFilmByIdFromBd(IDbQueries queries){
        this.queries = queries;
    }

    public void execute(int id){
        executor.execute(() -> {
            queries.getAppDatabase().dao().deleteById(id);
        });
    }
}
