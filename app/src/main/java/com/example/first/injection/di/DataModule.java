package com.example.first.injection.di;

import com.example.first.data.httpqueries.IAPI;
import com.example.first.data.httpqueries.RetrofitClient;
import com.example.first.domain.interfaces.IRetrofit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    IRetrofit provideRequests(){
        return new RetrofitClient();
    }
}
