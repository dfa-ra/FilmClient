package com.example.first.presentation.descriptionActivity;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.room.rxjava3.EmptyResultSetException;

import com.example.first.domain.models.LongFilmModel;
import com.example.first.domain.usecase.outputUsecase.GetFilmPoster;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromBd;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromLocal;

import java.util.concurrent.CompletableFuture;

public class DescriptionViewModel extends ViewModel {

    private final GetFilmPoster getFilmPoster;
    private final GetLongFilmInformationByIdFromBd getLongFilmInformationByIdFromBd;
    private final GetLongFilmInformationByIdFromLocal getLongFilmInformationByIdFromLocal;

    public DescriptionViewModel(GetFilmPoster getFilmPoster,
                                GetLongFilmInformationByIdFromBd getLongFilmInformationByIdFromBd,
                                GetLongFilmInformationByIdFromLocal getLongFilmInformationByIdFromLocal){
        this.getFilmPoster = getFilmPoster;
        this.getLongFilmInformationByIdFromBd = getLongFilmInformationByIdFromBd;
        this.getLongFilmInformationByIdFromLocal = getLongFilmInformationByIdFromLocal;
    }

    public Bitmap getPoster(String posterUrl){
        return getFilmPoster.execute(posterUrl);
    }

    public CompletableFuture<LongFilmModel> getLongFilmModelLocal(int id){
        CompletableFuture<LongFilmModel> lfilm = getLongFilmInformationByIdFromBd.execute(id)
                .exceptionally(ex -> {
                    if (ex instanceof EmptyResultSetException) {
                        Log.i("aa55", "ошибка на отсутсвие в бд обработана");
                        return getLongFilmInformationByIdFromLocal.execute(id);
                    } else {
                        // Обработка другой ошибки
                        return null;
                    }
                });
        return lfilm;
    }
}
