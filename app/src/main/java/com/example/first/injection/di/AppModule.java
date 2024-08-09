package com.example.first.injection.di;

import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByName;
import com.example.first.domain.usecase.outputUsecase.AllToShortFilmsInformation;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;
import com.example.first.presentation.Fragments.favoritesFragment.FavoritesViewModelFactory;
import com.example.first.presentation.Fragments.mainFragment.MainViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    MainViewModelFactory provideMainViewModelFactory(
            GetFilmInformationByName getFilmInformationByName,
            AllToShortFilmsInformation allToShortFilmsInformation,
            GetFilmInformationByCollection getFilmInformationByCollection
    ){
        return new MainViewModelFactory(
                getFilmInformationByName,
                allToShortFilmsInformation,
                getFilmInformationByCollection);
    }

    @Provides
    FavoritesViewModelFactory provideFavoritesViewModelFactory(
            GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb
    ){
        return new FavoritesViewModelFactory(
                getShortInformationAboutFilmsDb
        );
    }

}
