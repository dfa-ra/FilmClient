package com.example.first.presentation.Fragments.favoritesFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;

import java.util.List;

public class FavoritesViewModel extends ViewModel {

    private final MutableLiveData<List<ShortFilmModel>> favoritesList = new MutableLiveData<>();

    private final GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb;

    public FavoritesViewModel(GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb) {
        this.getShortInformationAboutFilmsDb = getShortInformationAboutFilmsDb;
    }

    public LiveData<List<ShortFilmModel>> getItems() {
        return favoritesList;
    }

    public void addToFavoritesList() {
        favoritesList.setValue(getShortInformationAboutFilmsDb.execute());
    }
}
