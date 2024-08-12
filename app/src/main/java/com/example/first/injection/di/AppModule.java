package com.example.first.injection.di;

import android.content.Context;

import com.example.first.domain.interfaces.IDbQueries;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByName;
import com.example.first.domain.usecase.logicsUsecase.SelectedFilmToFavorites;
import com.example.first.domain.usecase.outputUsecase.AllToShortFilmsInformation;
import com.example.first.domain.usecase.outputUsecase.GetFilmPoster;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationById;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;
import com.example.first.presentation.descriptionActivity.DescriptionViewModelFactory;
import com.example.first.presentation.mainActivity.Fragments.SendViewModelFactory;
import com.example.first.presentation.mainActivity.Fragments.favoritesFragment.FavoritesViewModelFactory;
import com.example.first.presentation.mainActivity.Fragments.mainFragment.MainViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    Context provideContext(){
        return context;
    }


    @Provides
    MainViewModelFactory provideMainViewModelFactory(
            GetFilmInformationByName getFilmInformationByName,
            AllToShortFilmsInformation allToShortFilmsInformation,
            GetFilmInformationByCollection getFilmInformationByCollection,
            GetLongFilmInformationById getLongFilmInformationById
    ){
        return new MainViewModelFactory(
                getFilmInformationByName,
                allToShortFilmsInformation,
                getFilmInformationByCollection,
                getLongFilmInformationById);
    }

    @Provides
    FavoritesViewModelFactory provideFavoritesViewModelFactory(
            GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb,
            GetLongFilmInformationById getLongFilmInformationById
    ){
        return new FavoritesViewModelFactory(
                getShortInformationAboutFilmsDb,
                getLongFilmInformationById
        );
    }

    @Provides
    SendViewModelFactory provideSendViewModelFactory(
            SelectedFilmToFavorites selectedFilmToFavorites
    ){
        return new SendViewModelFactory(
                selectedFilmToFavorites
        );
    }

    @Provides
    DescriptionViewModelFactory provideDescriptionViewModelFactory(
            GetFilmPoster getFilmPoster
    ){
        return new DescriptionViewModelFactory(
                getFilmPoster
        );
    }
}
