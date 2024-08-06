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

public class SendViewModel extends ViewModel implements DataFetchCallback {

    private final MutableLiveData<ShortFilmModel> selectedItem = new MutableLiveData<>();
    private final MutableLiveData<List<ShortFilmModel>> itemsLiveData = new MutableLiveData<>();


    private final Requests requestFilm;
    private final GetFilmInformationById getFilmInformationById;
    private final SelectedFilmToFavorites selectedFilmToFavorites;
    private final GetFilmsInformationByName getFilmsInformationByName;
    private final GetShortFilmInformationById getShortFilmInformationById;
    private final GetShortInformationAboutFilms getShortInformationAboutFilms;
    private final GetFilmInformationByCollection getFilmInformationByCollection;

    public SendViewModel(){
        requestFilm = new HttpQueries();
        getFilmInformationById = new GetFilmInformationById(requestFilm, this);
        selectedFilmToFavorites = new SelectedFilmToFavorites();
        getFilmsInformationByName = new GetFilmsInformationByName(requestFilm, getFilmInformationById, this);
        getFilmInformationByCollection = new GetFilmInformationByCollection(requestFilm, getFilmInformationById, this);
        getShortFilmInformationById = new GetShortFilmInformationById();
        getShortInformationAboutFilms = new GetShortInformationAboutFilms();

        getFilmInformationByCollection.execute(CollectionType.TOP_250_MOVIES
                .getNameCollections(), 1);
    }

    public void selectItem(ShortFilmModel item) {
        selectedItem.setValue(item);
        selectedFilmToFavorites.execute(item.kinopoiskId);
    }

    public LiveData<ShortFilmModel> getSelectedItem() {
        return selectedItem;
    }

    public void setItems(List<ShortFilmModel> items) {
        itemsLiveData.setValue(items);
    }

    public LiveData<List<ShortFilmModel>> getItems() {
        return itemsLiveData;
    }

    @Override
    public void onDataFetched() {

        Log.d("msRepositoryImplTag", "onDataFetched");
        setItems(getShortInformationAboutFilms.execute());
    }

    public void searchFilmByName(String name) {
        getFilmsInformationByName.execute(name, 1);
    }
}