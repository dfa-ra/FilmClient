package com.example.first.injection.di;

import android.content.Context;

import com.example.first.domain.usecase.dbUsecase.DeleteFilmByIdFromBd;
import com.example.first.domain.usecase.dbUsecase.UpdateComment;
import com.example.first.domain.usecase.dbUsecase.UpdateIsReadable;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByName;
import com.example.first.domain.usecase.logicsUsecase.MergeFlowFromDbAndApi;
import com.example.first.domain.usecase.logicsUsecase.SelectedFilmToFavorites;
import com.example.first.domain.usecase.outputUsecase.AllToShortFilmsInformation;
import com.example.first.domain.usecase.outputUsecase.GetFilmPoster;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromBd;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromLocal;
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
            GetFilmInformationByName getFilmsInformationByName,
            AllToShortFilmsInformation allToShortFilmsInformation,
            GetFilmInformationByCollection getFilmInformationByCollection,
            GetLongFilmInformationByIdFromLocal getLongFilmInformationByIdFromLocal,
            MergeFlowFromDbAndApi mergeFlowFromDbAndApi,
            GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb)
    {
        return new MainViewModelFactory(
                getFilmsInformationByName,
                allToShortFilmsInformation,
                getFilmInformationByCollection,
                getLongFilmInformationByIdFromLocal,
                mergeFlowFromDbAndApi,
                getShortInformationAboutFilmsDb);
    }

    @Provides
    FavoritesViewModelFactory provideFavoritesViewModelFactory(
            GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb,
            GetLongFilmInformationByIdFromBd getLongFilmInformationById,
            DeleteFilmByIdFromBd deleteFilmByIdFromBd,
            UpdateIsReadable updateIsReadable,
            UpdateComment updateComment
    ){
        return new FavoritesViewModelFactory(
                getShortInformationAboutFilmsDb,
                getLongFilmInformationById,
                deleteFilmByIdFromBd,
                updateIsReadable,
                updateComment
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
            GetFilmPoster getFilmPoster,
            GetLongFilmInformationByIdFromBd getLongFilmInformationByIdFromBd,
            GetLongFilmInformationByIdFromLocal getLongFilmInformationByIdFromLocal
    ){
        return new DescriptionViewModelFactory(
                        getFilmPoster,
                getLongFilmInformationByIdFromLocal,
                getLongFilmInformationByIdFromBd
                );
    }

}
