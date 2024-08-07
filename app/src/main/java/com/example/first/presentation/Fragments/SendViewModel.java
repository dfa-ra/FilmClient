package com.example.first.presentation.Fragments;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.logicsUsecase.SelectedFilmToFavorites;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class SendViewModel extends ViewModel{

    private final List<ShortFilmModel> allSelectedItem = new ArrayList<>();

    private final MutableLiveData<ShortFilmModel> selectedItem = new MutableLiveData<>();

    private final SelectedFilmToFavorites selectedFilmToFavorites;

    public SendViewModel(){
        Log.i("SendViewModel", "init send view model");
        selectedFilmToFavorites = new SelectedFilmToFavorites();
    }

    public boolean selectItem(ShortFilmModel item) {
        for (ShortFilmModel film: allSelectedItem){
            if (film.kinopoiskId == item.kinopoiskId) return false;
        }
        allSelectedItem.add(item);
        selectedItem.setValue(item);
        selectedFilmToFavorites.execute(item.kinopoiskId);
        return true;
    }

    public LiveData<ShortFilmModel> getSelectedItem() {
        return selectedItem;
    }
}