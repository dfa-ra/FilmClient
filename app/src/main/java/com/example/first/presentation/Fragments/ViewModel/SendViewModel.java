package com.example.first.presentation.Fragments.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.data.httpqueries.HttpQueries;
import com.example.first.data.httpqueries.RequestFilm;
import com.example.first.domain.common.enums.CollectionType;
import com.example.first.domain.interfaces.DataFetchCallback;
import com.example.first.domain.interfaces.Requests;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationById;
import com.example.first.domain.usecase.logicsUsecase.GetFilmsInformationByName;
import com.example.first.domain.usecase.logicsUsecase.SelectedFilmToFavorites;
import com.example.first.domain.usecase.outputUsecase.GetShortFilmInformationById;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilms;

import java.util.Collections;
import java.util.List;

public class SendViewModel extends ViewModel{

    private final MutableLiveData<ShortFilmModel> selectedItem = new MutableLiveData<>();

    private final SelectedFilmToFavorites selectedFilmToFavorites;

    public SendViewModel(){
        selectedFilmToFavorites = new SelectedFilmToFavorites();
    }

    public void selectItem(ShortFilmModel item) {
        selectedItem.setValue(item);
        selectedFilmToFavorites.execute(item.kinopoiskId);
    }

    public LiveData<ShortFilmModel> getSelectedItem() {
        return selectedItem;
    }
}