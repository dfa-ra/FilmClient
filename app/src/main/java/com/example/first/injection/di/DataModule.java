package com.example.first.injection.di;

import android.content.Context;

import com.example.first.data.dbqueries.LocalDB;
import com.example.first.data.dbqueries.MainDB;
import com.example.first.data.httpqueries.RetrofitClient;
import com.example.first.domain.interfaces.IDbQueries;
import com.example.first.domain.interfaces.ILocalDB;
import com.example.first.domain.interfaces.IRetrofit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    IRetrofit provideRetrofit(){
        return new RetrofitClient();
    }

    @Provides
    @Singleton
    IDbQueries provideDbQueries(Context context){
        return new MainDB(context);
    }

    @Provides
    @Singleton
    ILocalDB provideLocalDB(){
        return new LocalDB();
    }
}
