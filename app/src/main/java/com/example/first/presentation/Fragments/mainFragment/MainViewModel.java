package com.example.first.presentation.Fragments.mainFragment;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.domain.common.enums.CollectionType;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.logicsUsecase.GetFilmsInformationByName;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsLocal;

import java.util.List;


public class MainViewModel extends ViewModel{

    private final MutableLiveData<List<ShortFilmModel>> itemsLiveData = new MutableLiveData<>();

    private final GetFilmsInformationByName getFilmsInformationByName;
    private final GetShortInformationAboutFilmsLocal getShortInformationAboutFilmsLocal;
    private final GetFilmInformationByCollection getFilmInformationByCollection;

    public MainViewModel(
            GetFilmsInformationByName getFilmsInformationByName,
            GetShortInformationAboutFilmsLocal getShortInformationAboutFilmsLocal,
            GetFilmInformationByCollection getFilmInformationByCollection) {

        this.getFilmsInformationByName = getFilmsInformationByName;
        this.getFilmInformationByCollection = getFilmInformationByCollection;
        this.getShortInformationAboutFilmsLocal = getShortInformationAboutFilmsLocal;

        init();
    }

    public void init(){
        getFilmInformationByCollection.execute(CollectionType.TOP_250_MOVIES.getNameCollections(), 1);
    }

    public void setItems(List<ShortFilmModel> items) {
        itemsLiveData.setValue(items);
        Log.d("msRepositoryImplTag", "setItems");
    }

    public LiveData<List<ShortFilmModel>> getItems() {
        Log.d("msRepositoryImplTag", "getItems");
        return itemsLiveData;
    }

    public void updateShortInformationAboutFilms() {
        Log.d("msRepositoryImplTag", "onDataFetched");
        setItems(getShortInformationAboutFilmsLocal.execute());
    }

    public void searchFilmByName(String name) {
        getFilmsInformationByName.execute(name, 1);
    }
}
