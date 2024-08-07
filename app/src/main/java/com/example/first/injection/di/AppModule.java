package com.example.first.injection.di;

import com.example.first.presentation.Fragments.mainFragment.MainViewModelFactory;

import dagger.Module;

@Module
public class AppModule {

    public MainViewModelFactory provideMainViewModelFactory(

    ){
        return new MainViewModelFactory()
    }

}
