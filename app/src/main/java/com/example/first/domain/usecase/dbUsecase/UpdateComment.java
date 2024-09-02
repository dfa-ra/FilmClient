package com.example.first.domain.usecase.dbUsecase;

import com.example.first.domain.interfaces.IDbQueries;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UpdateComment {
    private final IDbQueries db;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public UpdateComment(IDbQueries db){
        this.db = db;
    }

    public void execute(int id, String comment){
        executor.execute(() -> db.getAppDatabase().dao().updateComment(id, comment));
    }
}
