package com.example.first.presentation.mainActivity.Fragments.mainFragment;


import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.domain.common.enums.CollectionType;
import com.example.first.domain.models.LongFilmModel;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByName;
import com.example.first.domain.usecase.logicsUsecase.MergeFlowFromDbAndApi;
import com.example.first.domain.usecase.outputUsecase.AllToShortFilmsInformation;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromBd;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromLocal;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.Getter;


public class MainViewModel extends ViewModel{

    private final MutableLiveData<List<ShortFilmModel>> itemsLiveData = new MutableLiveData<>();

    @Getter
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(true);

    private final GetFilmInformationByName getFilmsInformationByName;
    private final AllToShortFilmsInformation allToShortFilmsInformation;
    private final GetFilmInformationByCollection getFilmInformationByCollection;
    private final MergeFlowFromDbAndApi mergeFlowFromDbAndApi;
    private final GetLongFilmInformationByIdFromLocal getLongFilmInformationByIdFromLocal;
    private final GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb;

    public MainViewModel(
            GetFilmInformationByName getFilmsInformationByName,
            AllToShortFilmsInformation allToShortFilmsInformation,
            GetFilmInformationByCollection getFilmInformationByCollection,
            GetLongFilmInformationByIdFromLocal getLongFilmInformationByIdFromLocal,
            MergeFlowFromDbAndApi mergeFlowFromDbAndApi,
            GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb) {

        this.getFilmsInformationByName = getFilmsInformationByName;
        this.getFilmInformationByCollection = getFilmInformationByCollection;
        this.allToShortFilmsInformation = allToShortFilmsInformation;
        this.getLongFilmInformationByIdFromLocal = getLongFilmInformationByIdFromLocal;
        this.mergeFlowFromDbAndApi = mergeFlowFromDbAndApi;
        this.getShortInformationAboutFilmsDb = getShortInformationAboutFilmsDb;

        init();
    }

    public void init(){
       searchFilmByCollection(CollectionType.TOP_250_MOVIES);
    }

    public void setItems(List<ShortFilmModel> items) {
        itemsLiveData.setValue(items);
    }

    public LiveData<List<ShortFilmModel>> getItems() {
        return itemsLiveData;
    }

    public void searchFilmByName(String name) {
        itemsLiveData.setValue(new ArrayList<>());
        isLoading.setValue(true);
        getFilmsInformationByName.execute(name, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SingleObserver<List<ShortFilmModel>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onSuccess(@NonNull List<ShortFilmModel> shortFilmModels) {
                    setItems(shortFilmModels);
                    isLoading.setValue(false);
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            });
    }

    @SuppressLint("CheckResult")
    public void searchFilmByCollection(CollectionType collectionType){
        itemsLiveData.setValue(new ArrayList<>());
        isLoading.setValue(true);
        mergeFlowFromDbAndApi.execute(
                getShortInformationAboutFilmsDb.execute(),
                getFilmInformationByCollection.execute(collectionType.getNameCollections(), 1)
                ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> {
                                    itemsLiveData.setValue(item);
                                    isLoading.setValue(false);
                        },
                        throwable -> {
                            Log.e("UserViewModel", "Unable to get users", throwable);
                        }
                );

    }

    public LongFilmModel getLongFilmModel(ShortFilmModel shortFilmModel){
        return getLongFilmInformationByIdFromLocal.execute(shortFilmModel.kinopoiskId);
    }
}
