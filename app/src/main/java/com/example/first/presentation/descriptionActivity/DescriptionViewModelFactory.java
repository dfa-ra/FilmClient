package com.example.first.presentation.descriptionActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.domain.usecase.outputUsecase.GetFilmPoster;

public class DescriptionViewModelFactory implements ViewModelProvider.Factory {

    private final GetFilmPoster getFilmPoster;

    public DescriptionViewModelFactory(GetFilmPoster getFilmPoster){
        this.getFilmPoster = getFilmPoster;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DescriptionViewModel(getFilmPoster);
    }
}
