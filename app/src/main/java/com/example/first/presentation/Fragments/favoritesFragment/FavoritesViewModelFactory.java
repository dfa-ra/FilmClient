package com.example.first.presentation.Fragments.favoritesFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;

public class FavoritesViewModelFactory implements ViewModelProvider.Factory {

    private final GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb;

    public FavoritesViewModelFactory(
            GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb
    ){
        this.getShortInformationAboutFilmsDb = getShortInformationAboutFilmsDb;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FavoritesViewModel(getShortInformationAboutFilmsDb);
    }
}
