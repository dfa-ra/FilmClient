package com.example.first.presentation.descriptionActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.domain.usecase.outputUsecase.GetFilmPoster;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromBd;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromLocal;

public class DescriptionViewModelFactory implements ViewModelProvider.Factory {

    private final GetFilmPoster getFilmPoster;
    private final GetLongFilmInformationByIdFromBd getLongFilmInformationByIdFromBd;
    private final GetLongFilmInformationByIdFromLocal getLongFilmInformationByIdFromLocal;

    public DescriptionViewModelFactory(GetFilmPoster getFilmPoster,
                                       GetLongFilmInformationByIdFromLocal getLongFilmInformationByIdFromLocal,
                                       GetLongFilmInformationByIdFromBd getLongFilmInformationByIdFromBd){
        this.getFilmPoster = getFilmPoster;
        this.getLongFilmInformationByIdFromLocal = getLongFilmInformationByIdFromLocal;
        this.getLongFilmInformationByIdFromBd = getLongFilmInformationByIdFromBd;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DescriptionViewModel(getFilmPoster, getLongFilmInformationByIdFromBd, getLongFilmInformationByIdFromLocal);
    }
}
