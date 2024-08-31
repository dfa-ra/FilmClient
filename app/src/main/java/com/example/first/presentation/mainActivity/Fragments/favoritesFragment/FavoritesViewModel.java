package com.example.first.presentation.mainActivity.Fragments.favoritesFragment;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.dbUsecase.DeleteFilmByIdFromBd;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationById;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritesViewModel extends ViewModel {

    private final MutableLiveData<List<ShortFilmModel>> favoritesList = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb;
    private final GetLongFilmInformationById getLongFilmInformationById;
    private final DeleteFilmByIdFromBd deleteFilmByIdFromBd;

    public FavoritesViewModel(GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb,
                              GetLongFilmInformationById getLongFilmInformationById,
                              DeleteFilmByIdFromBd deleteFilmByIdFromBd) {
        this.getShortInformationAboutFilmsDb = getShortInformationAboutFilmsDb;
        this.getLongFilmInformationById = getLongFilmInformationById;
        this.deleteFilmByIdFromBd = deleteFilmByIdFromBd;
        loadFilms();
    }

    public LiveData<List<ShortFilmModel>> getItems() {
        return favoritesList;
    }

    private void loadFilms() {
        Disposable disposable = getShortInformationAboutFilmsDb.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        favoritesList::setValue,
                        throwable -> {
                            Log.e("UserViewModel", "Unable to get users", throwable);
                        }
                );
        compositeDisposable.add(disposable);
    }

    public void deleteFilmById(int id){
        deleteFilmByIdFromBd.execute(id);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
