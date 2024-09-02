package com.example.first.domain.usecase.dbUsecase;

import com.example.first.domain.interfaces.IDbQueries;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UpdateIsReadable {
    private final IDbQueries db;

    private final Executor executor = Executors.newSingleThreadExecutor();


    public UpdateIsReadable(IDbQueries db){
        this.db = db;
    }

    public void execute(int id, boolean isReadable){
        executor.execute(() -> db.getAppDatabase().dao().updateIsReadable(id, isReadable));
    }
}
