package com.example.first.Fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.filmStrip.FilmItem;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<FilmItem> selectedItem = new MutableLiveData<>();

    public void selectItem(FilmItem item) {
        selectedItem.setValue(item);
    }

    public LiveData<FilmItem> getSelectedItem() {
        return selectedItem;
    }
}