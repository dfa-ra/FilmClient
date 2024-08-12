package com.example.first.presentation.descriptionActivity;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.first.domain.usecase.outputUsecase.GetFilmPoster;

public class DescriptionViewModel extends ViewModel {

    private final GetFilmPoster getFilmPoster;

    public DescriptionViewModel(GetFilmPoster getFilmPoster){
        this.getFilmPoster = getFilmPoster;
    }

    public Bitmap getPoster(String posterUrl){
        return getFilmPoster.execute(posterUrl);
    }
}
