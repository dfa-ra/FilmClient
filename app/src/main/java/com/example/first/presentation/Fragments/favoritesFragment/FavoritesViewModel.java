package com.example.first.presentation.Fragments.favoritesFragment;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.logicsUsecase.SetFilmInformationToDb;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;

import java.util.List;

public class FavoritesViewModel extends ViewModel {

    private final MutableLiveData<List<ShortFilmModel>> favoritesList = new MutableLiveData<>();

    private final GetShortInformationAboutFilmsDb getShortInformationAboutFilms;
    private final SetFilmInformationToDb setFilmInformationToDb;

    public FavoritesViewModel(GetShortInformationAboutFilmsDb getShortInformationAboutFilms, SetFilmInformationToDb setFilmInformationToDb){

        this.getShortInformationAboutFilms = getShortInformationAboutFilms;
        this.setFilmInformationToDb = setFilmInformationToDb;

        favoritesList.setValue(getShortInformationAboutFilms.execute());
    }

    public LiveData<List<ShortFilmModel>> getItems(){
        return favoritesList;
    }

    public void addToFavoritesList(ShortFilmModel item){
        setFilmInformationToDb.execute(item);
        favoritesList.setValue(getShortInformationAboutFilms.execute());
    }
}
