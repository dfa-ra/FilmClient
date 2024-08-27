package com.example.first.presentation.mainActivity.customBottomSheetDialog;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.domain.usecase.logicsUsecase.DeleteFilmById;
import com.example.first.presentation.mainActivity.Fragments.mainFragment.MainViewModel;

public class CustomBottomSheetDialogViewModelFactory implements ViewModelProvider.Factory {
    private final DeleteFilmById deleteFilmById;

    public CustomBottomSheetDialogViewModelFactory(DeleteFilmById deleteFilmById){
        this.deleteFilmById = deleteFilmById;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CustomBottomSheetDialogViewModel(deleteFilmById);
    }
}
