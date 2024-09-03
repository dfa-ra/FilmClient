package com.example.first.presentation.mainActivity.Fragments.mainFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.domain.usecase.dbUsecase.GetFilmsByCollectionFromBd;
import com.example.first.domain.usecase.dbUsecase.GetFilmsByTextFromBd;
import com.example.first.domain.usecase.outputUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.outputUsecase.GetFilmInformationByName;
import com.example.first.domain.usecase.logicsUsecase.MergeFlowFromDbAndApi;
import com.example.first.domain.usecase.logicsUsecase.AllToShortFilmsInformation;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromLocal;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final GetFilmInformationByName getFilmsInformationByName;
    private final GetFilmInformationByCollection getFilmInformationByCollection;
    private final MergeFlowFromDbAndApi mergeFlowFromDbAndApi;
    private final GetLongFilmInformationByIdFromLocal getLongFilmInformationByIdFromLocal;
    private final GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb;


    public MainViewModelFactory(
            GetFilmInformationByName getFilmsInformationByName,
            GetFilmInformationByCollection getFilmInformationByCollection,
            GetLongFilmInformationByIdFromLocal getLongFilmInformationByIdFromLocal,
            MergeFlowFromDbAndApi mergeFlowFromDbAndApi,
            GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb) {

        this.getFilmsInformationByName = getFilmsInformationByName;
        this.getFilmInformationByCollection = getFilmInformationByCollection;
        this.getLongFilmInformationByIdFromLocal = getLongFilmInformationByIdFromLocal;
        this.mergeFlowFromDbAndApi = mergeFlowFromDbAndApi;
        this.getShortInformationAboutFilmsDb = getShortInformationAboutFilmsDb;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(getFilmsInformationByName, getFilmInformationByCollection, getLongFilmInformationByIdFromLocal, mergeFlowFromDbAndApi, getShortInformationAboutFilmsDb);
    }
}
