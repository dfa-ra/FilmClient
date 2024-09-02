package com.example.first.presentation.mainActivity.Fragments.mainFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByName;
import com.example.first.domain.usecase.logicsUsecase.MergeFlowFromDbAndApi;
import com.example.first.domain.usecase.outputUsecase.AllToShortFilmsInformation;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromBd;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromLocal;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final GetFilmInformationByName getFilmsInformationByName;
    private final AllToShortFilmsInformation allToShortFilmsInformation;
    private final GetFilmInformationByCollection getFilmInformationByCollection;
    private final MergeFlowFromDbAndApi mergeFlowFromDbAndApi;
    private final GetLongFilmInformationByIdFromLocal getLongFilmInformationByIdFromLocal;
    private final GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb;


    public MainViewModelFactory(
            GetFilmInformationByName getFilmsInformationByName,
            AllToShortFilmsInformation allToShortFilmsInformation,
            GetFilmInformationByCollection getFilmInformationByCollection,
            GetLongFilmInformationByIdFromLocal getLongFilmInformationByIdFromLocal,
            MergeFlowFromDbAndApi mergeFlowFromDbAndApi,
            GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb) {

        this.getFilmsInformationByName = getFilmsInformationByName;
        this.getFilmInformationByCollection = getFilmInformationByCollection;
        this.allToShortFilmsInformation = allToShortFilmsInformation;
        this.getLongFilmInformationByIdFromLocal = getLongFilmInformationByIdFromLocal;
        this.mergeFlowFromDbAndApi = mergeFlowFromDbAndApi;
        this.getShortInformationAboutFilmsDb = getShortInformationAboutFilmsDb;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(getFilmsInformationByName, allToShortFilmsInformation, getFilmInformationByCollection, getLongFilmInformationByIdFromLocal, mergeFlowFromDbAndApi, getShortInformationAboutFilmsDb);
    }
}
