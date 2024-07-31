package com.example.first.presentation.Fragments.ViewModel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.data.DataFetchCallback;
import com.example.first.data.FilmsRepositoryImpl;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.repository.FilmsRepository;
import com.example.first.domain.usecase.GetShortFilmsInformationByNameUseCase;
import com.example.first.domain.usecase.GetShortInformationAboutFilmsUseCase;
import com.example.first.domain.usecase.SelectedFilmToFavoritesUseCase;

import java.util.List;

public class FragmentsViewModel extends ViewModel implements DataFetchCallback {

    private final MutableLiveData<ShortFilmModel> selectedItem = new MutableLiveData<>();
    private final MutableLiveData<List<ShortFilmModel>> itemsLiveData = new MutableLiveData<>();


    private FilmsRepository filmsRepository;
    private GetShortInformationAboutFilmsUseCase getShortInformationAboutFilmsUseCase;
    private SelectedFilmToFavoritesUseCase selectedFilmToFavoritesUseCase;
    private GetShortFilmsInformationByNameUseCase getShortFilmsInformationByNameUseCase;

    public FragmentsViewModel(){
        filmsRepository = new FilmsRepositoryImpl(this);
        getShortInformationAboutFilmsUseCase = new GetShortInformationAboutFilmsUseCase(filmsRepository);
        selectedFilmToFavoritesUseCase = new SelectedFilmToFavoritesUseCase(filmsRepository);
        getShortFilmsInformationByNameUseCase = new GetShortFilmsInformationByNameUseCase(filmsRepository);
    }

    public void selectItem(ShortFilmModel item) {
        selectedItem.setValue(item);
        selectedFilmToFavoritesUseCase.execute(item.kinopoiskId);
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

    public void searchFilmByName(String name){

        Log.i(FilmsRepositoryImpl.Tag, "search in View Model");
        getShortFilmsInformationByNameUseCase.execute(name, 1);
    }
}