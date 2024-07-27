package com.example.first.Fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.filmStrip.FilmItem;

import java.util.List;

import lombok.Getter;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<FilmItem> selectedItem = new MutableLiveData<>();

    private final MutableLiveData<List<FilmItem>> itemsLiveData = new MutableLiveData<>();

    public void selectItem(FilmItem item) {
        selectedItem.setValue(item);
    }

    public LiveData<FilmItem> getSelectedItem() {
        return selectedItem;
    }

    public void setItems(List<FilmItem> items) {
        itemsLiveData.setValue(items);
    }

    public LiveData<List<FilmItem>> getItems() {
        return itemsLiveData;
    }
}