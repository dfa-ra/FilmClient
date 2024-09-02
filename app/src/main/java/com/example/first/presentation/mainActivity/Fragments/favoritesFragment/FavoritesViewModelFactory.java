package com.example.first.presentation.mainActivity.Fragments.favoritesFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.domain.usecase.dbUsecase.DeleteFilmByIdFromBd;
import com.example.first.domain.usecase.dbUsecase.UpdateComment;
import com.example.first.domain.usecase.dbUsecase.UpdateIsReadable;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromBd;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;

public class FavoritesViewModelFactory implements ViewModelProvider.Factory {

    private final GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb;
    private final GetLongFilmInformationByIdFromBd getLongFilmInformationById;
    private final DeleteFilmByIdFromBd deleteFilmByIdFromBd;
    private final UpdateComment updateComment;
    private final UpdateIsReadable updateIsReadable;

    public FavoritesViewModelFactory(
            GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb,
            GetLongFilmInformationByIdFromBd getLongFilmInformationById,
            DeleteFilmByIdFromBd deleteFilmByIdFromBd,
            UpdateIsReadable updateIsReadable,
            UpdateComment updateComment){
        this.getShortInformationAboutFilmsDb = getShortInformationAboutFilmsDb;
        this.getLongFilmInformationById = getLongFilmInformationById;
        this.deleteFilmByIdFromBd = deleteFilmByIdFromBd;
        this.updateComment = updateComment;
        this.updateIsReadable = updateIsReadable;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FavoritesViewModel(getShortInformationAboutFilmsDb, getLongFilmInformationById, deleteFilmByIdFromBd, updateIsReadable, updateComment);
    }
}
