package com.example.first.presentation.Fragments.mainFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByName;
import com.example.first.domain.usecase.outputUsecase.AllToShortFilmsInformation;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final GetFilmInformationByName getFilmInformationByName;
    private final AllToShortFilmsInformation allToShortFilmsInformation;
    private final GetFilmInformationByCollection getFilmInformationByCollection;

    public MainViewModelFactory(
            GetFilmInformationByName getFilmInformationByName,
            AllToShortFilmsInformation allToShortFilmsInformation,
            GetFilmInformationByCollection getFilmInformationByCollection){

        this.getFilmInformationByName = getFilmInformationByName;
        this.allToShortFilmsInformation = allToShortFilmsInformation;
        this.getFilmInformationByCollection = getFilmInformationByCollection;

    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(getFilmInformationByName, allToShortFilmsInformation, getFilmInformationByCollection);
    }
}
