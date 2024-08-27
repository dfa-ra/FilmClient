package com.example.first.presentation.mainActivity.customBottomSheetDialog;

import androidx.lifecycle.ViewModel;

import com.example.first.domain.usecase.logicsUsecase.DeleteFilmById;

public class CustomBottomSheetDialogViewModel extends ViewModel {
    private final DeleteFilmById deleteFilmById;

    public CustomBottomSheetDialogViewModel(DeleteFilmById deleteFilmById){
        this.deleteFilmById = deleteFilmById;
    }

    public void deleteItemById(int id){
        deleteFilmById.execute(id);
    }
}
