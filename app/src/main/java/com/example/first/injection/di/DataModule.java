package com.example.first.injection.di;

import com.example.first.data.httpqueries.IRetrofit;
import com.example.first.data.httpqueries.RetrofitImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    IRetrofit provideRequests(){
        return new RetrofitImpl();
    }
}
