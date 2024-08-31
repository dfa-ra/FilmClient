package com.example.first.presentation.mainActivity.Fragments.favoritesFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.domain.usecase.dbUsecase.DeleteFilmByIdFromBd;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationById;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;

public class FavoritesViewModelFactory implements ViewModelProvider.Factory {

    private final GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb;
    private final GetLongFilmInformationById getLongFilmInformationById;
    public final DeleteFilmByIdFromBd deleteFilmByIdFromBd;

    public FavoritesViewModelFactory(
            GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb,
            GetLongFilmInformationById getLongFilmInformationById,
            DeleteFilmByIdFromBd deleteFilmByIdFromBd){
        this.getShortInformationAboutFilmsDb = getShortInformationAboutFilmsDb;
        this.getLongFilmInformationById = getLongFilmInformationById;
        this.deleteFilmByIdFromBd = deleteFilmByIdFromBd;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FavoritesViewModel(getShortInformationAboutFilmsDb, getLongFilmInformationById, deleteFilmByIdFromBd);
    }
}
