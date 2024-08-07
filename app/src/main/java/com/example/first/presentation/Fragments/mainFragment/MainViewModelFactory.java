package com.example.first.presentation.Fragments.mainFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.data.httpqueries.HttpQueries;
import com.example.first.domain.interfaces.DataFetchCallback;
import com.example.first.domain.interfaces.Requests;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationById;
import com.example.first.domain.usecase.logicsUsecase.GetFilmsInformationByName;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsLocal;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final GetFilmsInformationByName getFilmsInformationByName;
    private final GetShortInformationAboutFilmsLocal getShortInformationAboutFilmsLocal;
    private final GetFilmInformationByCollection getFilmInformationByCollection;

    public MainViewModelFactory(DataFetchCallback callback){

        Requests requestFilm = HttpQueries.getInstance();

        GetFilmInformationById getFilmInformationById = new GetFilmInformationById(requestFilm, callback);
        getFilmsInformationByName = new GetFilmsInformationByName(requestFilm,getFilmInformationById, callback);
        getShortInformationAboutFilmsLocal = new GetShortInformationAboutFilmsLocal();
        getFilmInformationByCollection = new GetFilmInformationByCollection(requestFilm, getFilmInformationById, callback);

    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(getFilmsInformationByName, getShortInformationAboutFilmsLocal, getFilmInformationByCollection);
    }
}
