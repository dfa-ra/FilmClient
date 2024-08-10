package com.example.first.presentation.Fragments.favoritesFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationById;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;

public class FavoritesViewModelFactory implements ViewModelProvider.Factory {

    private final GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb;
    private final GetLongFilmInformationById getLongFilmInformationById;

    public FavoritesViewModelFactory(
            GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb,
            GetLongFilmInformationById getLongFilmInformationById){
        this.getShortInformationAboutFilmsDb = getShortInformationAboutFilmsDb;
        this.getLongFilmInformationById = getLongFilmInformationById;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FavoritesViewModel(getShortInformationAboutFilmsDb, getLongFilmInformationById);
    }
}
