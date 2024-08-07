package com.example.first.presentation.Fragments.favoritesFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.domain.interfaces.DataFetchCallback;
import com.example.first.domain.usecase.logicsUsecase.SetFilmInformationToDb;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;

public class FavoritesViewModelFactory implements ViewModelProvider.Factory {

    private final GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb;
    private final SetFilmInformationToDb setFilmInformationToDb;

    public FavoritesViewModelFactory(){
        getShortInformationAboutFilmsDb = new GetShortInformationAboutFilmsDb();
        setFilmInformationToDb = new SetFilmInformationToDb();
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FavoritesViewModel(getShortInformationAboutFilmsDb, setFilmInformationToDb);
    }
}
