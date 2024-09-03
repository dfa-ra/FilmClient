package com.example.first.presentation.mainActivity.Fragments;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.logicsUsecase.SelectedFilmToFavorites;

import java.util.ArrayList;
import java.util.List;

public class SendViewModel extends ViewModel{

    private final List<ShortFilmModel> allSelectedItem = new ArrayList<>();

    private final MutableLiveData<ShortFilmModel> selectedItem = new MutableLiveData<>();

    private final SelectedFilmToFavorites selectedFilmToFavorites;

    public SendViewModel(SelectedFilmToFavorites selectedFilmToFavorites){
        this.selectedFilmToFavorites = selectedFilmToFavorites;
    }

    public boolean selectItem(ShortFilmModel item) {

        Log.d("aa66", "selectItem");
        if (item.isChecked) return false;
        
        allSelectedItem.add(item);
        selectedFilmToFavorites.execute(item.kinopoiskId);
        selectedItem.setValue(item);
        return true;
    }

    public LiveData<ShortFilmModel> getSelectedItem() {
        return selectedItem;
    }
}