package com.example.first.presentation.mainActivity.Fragments.mainFragment;


import android.annotation.SuppressLint;
import android.util.Log;

import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.data.common.enums.CollectionType;
import com.example.first.domain.models.LongFilmModel;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.outputUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.outputUsecase.GetFilmInformationByName;
import com.example.first.domain.usecase.logicsUsecase.MergeFlowFromDbAndApi;
import com.example.first.domain.usecase.logicsUsecase.AllToShortFilmsInformation;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromLocal;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.Getter;

public class MainViewModel extends ViewModel{

    private final MutableLiveData<List<ShortFilmModel>> itemsLiveData = new MutableLiveData<>();

    @Getter
    private final MutableLiveData<Integer> isLoading = new MutableLiveData<>(0);

    @Getter
    private Pair<String, String> lastQuery;

    private final GetFilmInformationByName getFilmsInformationByName;
    private final GetFilmInformationByCollection getFilmInformationByCollection;
    private final MergeFlowFromDbAndApi mergeFlowFromDbAndApi;
    private final GetLongFilmInformationByIdFromLocal getLongFilmInformationByIdFromLocal;
    private final GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb;

    public MainViewModel(
            GetFilmInformationByName getFilmsInformationByName,
            GetFilmInformationByCollection getFilmInformationByCollection,
            GetLongFilmInformationByIdFromLocal getLongFilmInformationByIdFromLocal,
            MergeFlowFromDbAndApi mergeFlowFromDbAndApi,
            GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb) {

        this.getFilmsInformationByName = getFilmsInformationByName;
        this.getFilmInformationByCollection = getFilmInformationByCollection;
        this.getLongFilmInformationByIdFromLocal = getLongFilmInformationByIdFromLocal;
        this.mergeFlowFromDbAndApi = mergeFlowFromDbAndApi;
        this.getShortInformationAboutFilmsDb = getShortInformationAboutFilmsDb;

        init();
    }

    public void init(){
       searchFilmByCollection(CollectionType.TOP_250_MOVIES.getNameCollections());
    }

    public void setItems(List<ShortFilmModel> items) {
        itemsLiveData.setValue(items);
    }

    public LiveData<List<ShortFilmModel>> getItems() {
        return itemsLiveData;
    }

    @SuppressLint("CheckResult")
    public void searchFilmByName(String name) {
        lastQuery = new Pair<>("searchFilmByName", name);;
        itemsLiveData.setValue(new ArrayList<>());
        isLoading.setValue(0);
        mergeFlowFromDbAndApi.execute(
                        getShortInformationAboutFilmsDb.execute(),
                        getFilmsInformationByName.execute(name, 1)
                ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> {
                            itemsLiveData.setValue(item);
                            isLoading.setValue(2);
                        },
                        throwable -> {
                            isLoading.setValue(1);
                            Log.e("UserViewModel", "Unable to get users", throwable);
                        }
                );
    }

    @SuppressLint("CheckResult")
    public void searchFilmByCollection(String collectionType){
        lastQuery = new Pair<>("searchFilmByCollection", collectionType);
        itemsLiveData.setValue(new ArrayList<>());
        isLoading.setValue(0);
        Log.d("aa11", "searchFilmByCollection");
        mergeFlowFromDbAndApi.execute(
                getShortInformationAboutFilmsDb.execute(),
                getFilmInformationByCollection.execute(collectionType, 1)
                ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> {
                                    itemsLiveData.setValue(item);
                                    isLoading.setValue(2);
                        },
                        throwable -> {
                            isLoading.setValue(1);
                            Log.e("UserViewModel", "Unable to get users", throwable);
                        }
                );

    }

    public LongFilmModel getLongFilmModel(ShortFilmModel shortFilmModel){
        return getLongFilmInformationByIdFromLocal.execute(shortFilmModel.kinopoiskId);
    }
}
