package com.example.first.presentation.mainActivity.customBottomSheetDialog;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.domain.usecase.dbUsecase.DeleteFilmByIdFromBd;

public class CustomBottomSheetDialogViewModelFactory implements ViewModelProvider.Factory {
    private final DeleteFilmByIdFromBd deleteFilmByIdFromBd;

    public CustomBottomSheetDialogViewModelFactory(DeleteFilmByIdFromBd deleteFilmByIdFromBd){
        this.deleteFilmByIdFromBd = deleteFilmByIdFromBd;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CustomBottomSheetDialogViewModel(deleteFilmByIdFromBd);
    }
}
