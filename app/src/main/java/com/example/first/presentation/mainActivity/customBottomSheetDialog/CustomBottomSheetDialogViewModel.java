package com.example.first.presentation.mainActivity.customBottomSheetDialog;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.first.domain.usecase.dbUsecase.DeleteFilmByIdFromBd;

public class CustomBottomSheetDialogViewModel extends ViewModel {
    private final DeleteFilmByIdFromBd deleteFilmByIdFromBd;


    public CustomBottomSheetDialogViewModel(DeleteFilmByIdFromBd deleteFilmByIdFromBd){
        this.deleteFilmByIdFromBd = deleteFilmByIdFromBd;
    }

    public void deleteItemById(int id){
        deleteFilmByIdFromBd.execute(id);
    }
}
