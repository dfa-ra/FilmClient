package com.example.first.presentation.Fragments.mainFragment;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.data.models.FilmModel;
import com.example.first.domain.common.enums.CollectionType;
import com.example.first.domain.models.LongFilmModel;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByName;
import com.example.first.domain.usecase.outputUsecase.AllToShortFilmsInformation;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationById;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainViewModel extends ViewModel{

    private final MutableLiveData<List<ShortFilmModel>> itemsLiveData = new MutableLiveData<>();

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
        getFilmsInformationByName.execute(name, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SingleObserver<List<FilmModel>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onSuccess(@NonNull List<FilmModel> filmModels) {
                    setItems(allToShortFilmsInformation.execute(filmModels));
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            });
    }

    public void searchFilmByCollection(CollectionType collectionType){
        getFilmInformationByCollection.execute(collectionType.getNameCollections(), 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SingleObserver<List<FilmModel>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onSuccess(@NonNull List<FilmModel> filmModels) {
                    setItems(allToShortFilmsInformation.execute(filmModels));
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            });
    }

    public LongFilmModel getLongFilmModel(ShortFilmModel shortFilmModel){
        return getLongFilmInformationById.execute(shortFilmModel.kinopoiskId);
    }

}
