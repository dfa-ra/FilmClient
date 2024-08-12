package com.example.first.presentation.mainActivity.Fragments;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.domain.usecase.logicsUsecase.SelectedFilmToFavorites;

public class SendViewModelFactory implements ViewModelProvider.Factory {

    private final SelectedFilmToFavorites selectedFilmToFavorites;

    public SendViewModelFactory(
            SelectedFilmToFavorites selectedFilmToFavorites){
        this.selectedFilmToFavorites = selectedFilmToFavorites;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SendViewModel(selectedFilmToFavorites);
    }
}
