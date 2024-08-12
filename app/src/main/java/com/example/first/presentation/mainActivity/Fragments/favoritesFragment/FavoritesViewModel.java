package com.example.first.presentation.mainActivity.Fragments.favoritesFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationById;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;

import java.util.List;

public class FavoritesViewModel extends ViewModel {

    private final MutableLiveData<List<ShortFilmModel>> favoritesList = new MutableLiveData<>();

    private final GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb;
    private final GetLongFilmInformationById getLongFilmInformationById;

    public FavoritesViewModel(GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb,
                              GetLongFilmInformationById getLongFilmInformationById) {
        this.getShortInformationAboutFilmsDb = getShortInformationAboutFilmsDb;
        this.getLongFilmInformationById = getLongFilmInformationById;
    }

    public LiveData<List<ShortFilmModel>> getItems() {
        return favoritesList;
    }

    public void addToFavoritesList() {
        favoritesList.setValue(getShortInformationAboutFilmsDb.execute());
    }
}
