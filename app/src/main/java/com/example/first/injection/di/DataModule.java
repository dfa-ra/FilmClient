package com.example.first.injection.di;

import com.example.first.data.httpqueries.HttpQueries;
import com.example.first.domain.interfaces.Requests;

import dagger.Module;

@Module
public class DataModule {

    public Requests provideRequests(){
        return HttpQueries.getInstance();
    }

}
