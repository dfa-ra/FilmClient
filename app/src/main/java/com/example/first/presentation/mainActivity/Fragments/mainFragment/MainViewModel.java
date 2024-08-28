package com.example.first.presentation.mainActivity.Fragments.mainFragment;


import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.common.enums.CollectionType;
import com.example.first.domain.models.LongFilmModel;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByName;
import com.example.first.domain.usecase.outputUsecase.AllToShortFilmsInformation;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationById;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
    private final GetLongFilmInformationById getLongFilmInformationById;

    public MainViewModel(
            GetFilmInformationByName getFilmsInformationByName,
            AllToShortFilmsInformation allToShortFilmsInformation,
            GetFilmInformationByCollection getFilmInformationByCollection,
            GetLongFilmInformationById getLongFilmInformationById) {

        this.getFilmsInformationByName = getFilmsInformationByName;
        this.getFilmInformationByCollection = getFilmInformationByCollection;
        this.allToShortFilmsInformation = allToShortFilmsInformation;
        this.getLongFilmInformationById = getLongFilmInformationById;

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

    public void searchFilmByCollection(CollectionType collectionType){
        itemsLiveData.setValue(new ArrayList<>());
        isLoading.setValue(true);
        getFilmInformationByCollection.execute(collectionType.getNameCollections(), 1)
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

    public void getLongFilmModel(ShortFilmModel shortFilmModel, Intent intent){
        getLongFilmInformationById.execute(shortFilmModel.kinopoiskId).thenAccept(
                longFilmModel -> {
                    intent.putExtra("filmModel", longFilmModel); //Optional parameters
                }
        );
    }
}
