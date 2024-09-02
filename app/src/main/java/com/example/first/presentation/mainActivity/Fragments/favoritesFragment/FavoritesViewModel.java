package com.example.first.presentation.mainActivity.Fragments.favoritesFragment;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.first.domain.models.LongFilmModel;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.dbUsecase.DeleteFilmByIdFromBd;
import com.example.first.domain.usecase.dbUsecase.UpdateComment;
import com.example.first.domain.usecase.dbUsecase.UpdateIsReadable;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromBd;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritesViewModel extends ViewModel {

    private final MutableLiveData<List<ShortFilmModel>> favoritesList = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb;
    private final GetLongFilmInformationByIdFromBd getLongFilmInformationByIdFromBd;
    private final DeleteFilmByIdFromBd deleteFilmByIdFromBd;
    private final UpdateComment updateComment;
    private final UpdateIsReadable updateIsReadable;

    public FavoritesViewModel(GetShortInformationAboutFilmsDb getShortInformationAboutFilmsDb,
                              GetLongFilmInformationByIdFromBd getLongFilmInformationById,
                              DeleteFilmByIdFromBd deleteFilmByIdFromBd,
                              UpdateIsReadable updateIsReadable,
                              UpdateComment updateComment) {
        this.getShortInformationAboutFilmsDb = getShortInformationAboutFilmsDb;
        this.getLongFilmInformationByIdFromBd = getLongFilmInformationById;
        this.deleteFilmByIdFromBd = deleteFilmByIdFromBd;
        this.updateComment = updateComment;
        this.updateIsReadable = updateIsReadable;
        loadFilms();
    }

    public LiveData<List<ShortFilmModel>> getItems() {
        return favoritesList;
    }

    private void loadFilms() {
        Log.d("aa66","loadFilms1");
        Disposable disposable = getShortInformationAboutFilmsDb.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        favoritesList::setValue,
                        throwable -> {
                            Log.e("UserViewModel", "Unable to get users", throwable);
                        }
                );
        Log.d("aa66","loadFilms2");
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

    public void updateComment(int id, String comment){
        updateComment.execute(id, comment);
    }

    public void updateIsReadable(int id, boolean isReadable){
        updateIsReadable.execute(id, isReadable);
    }

    public CompletableFuture<LongFilmModel> getLongFilmModel(ShortFilmModel shortFilmModel){
        return getLongFilmInformationByIdFromBd.execute(shortFilmModel.kinopoiskId);
    }
}
