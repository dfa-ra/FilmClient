package com.example.first.presentation.Fragments.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.data.DataFetchCallback;
import com.example.first.data.FilmRepositoryImpl;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.GetShortInformationAboutFilmsUseCase;
import com.example.first.presentation.Fragments.PopularFragment;

import java.util.List;

public class SharedViewModel extends ViewModel implements DataFetchCallback {
    private final MutableLiveData<ShortFilmModel> selectedItem = new MutableLiveData<>();
    private final MutableLiveData<List<ShortFilmModel>> itemsLiveData = new MutableLiveData<>();

    private FilmRepositoryImpl filmsRepository;
    private GetShortInformationAboutFilmsUseCase getShortInformationAboutFilmsUseCase;

    public SharedViewModel(){
        filmsRepository = new FilmRepositoryImpl(this);
        getShortInformationAboutFilmsUseCase = new GetShortInformationAboutFilmsUseCase(filmsRepository);
    }

    public void selectItem(ShortFilmModel item) {
        selectedItem.setValue(item);
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
        setItems(getShortInformationAboutFilmsUseCase.execute());
    }
}