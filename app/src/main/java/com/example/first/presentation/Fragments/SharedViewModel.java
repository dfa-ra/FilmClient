package com.example.first.presentation.Fragments;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.domain.models.LongFilmModel;
import com.example.first.domain.models.ShortFilmModel;

import java.util.List;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<ShortFilmModel> selectedItem = new MutableLiveData<>();

    private final MutableLiveData<List<ShortFilmModel>> itemsLiveData = new MutableLiveData<>();

    public void selectItem(ShortFilmModel item) {
        selectedItem.setValue(item);
    }

    public LiveData<ShortFilmModel> getSelectedItem() {
        return selectedItem;
    }

    public void setItems(List<ShortFilmModel> items) {
        Log.d(PopularFragment.Tag, items.toString());
        itemsLiveData.setValue(items);
        Log.d(PopularFragment.Tag, "puupupupupupup");
    }

    public LiveData<List<ShortFilmModel>> getItems() {
        return itemsLiveData;
    }
}