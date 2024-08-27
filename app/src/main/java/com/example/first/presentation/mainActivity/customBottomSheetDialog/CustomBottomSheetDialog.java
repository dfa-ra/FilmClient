package com.example.first.presentation.mainActivity.customBottomSheetDialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.R;
import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.injection.app.App;
import com.example.first.presentation.mainActivity.Fragments.SendViewModel;
import com.example.first.presentation.mainActivity.Fragments.SendViewModelFactory;
import com.example.first.presentation.mainActivity.Fragments.favoritesFragment.FavoritesViewModelFactory;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import javax.inject.Inject;

public class CustomBottomSheetDialog extends BottomSheetDialogFragment {

    private static final String ARG_KEY = "arg_key";
    private ShortFilmModel film;
    @Inject
    CustomBottomSheetDialogViewModelFactory customBottomSheetDialogViewModelFactory;

    CustomBottomSheetDialogViewModel cbsdv;

    public CustomBottomSheetDialog (ShortFilmModel filmModel) {
        this.film = filmModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getActivity() != null;
        ((App) getActivity().getApplication()).getAppComponent().inject(this);

        return inflater.inflate(R.layout.bottom_sheet_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cbsdv = new ViewModelProvider(requireActivity(), customBottomSheetDialogViewModelFactory).get(CustomBottomSheetDialogViewModel.class);

        // Привязка кнопок и установка действий
        Button buttonWatch = view.findViewById(R.id.buttonWatch);
        Button buttonDelete = view.findViewById(R.id.buttonDelete);

        buttonWatch.setOnClickListener(v -> {
            // Действие при нажатии кнопки Edit
            dismiss();
        });

        buttonDelete.setOnClickListener(v -> {
            cbsdv.deleteItemById(film.kinopoiskId);
            dismiss();
        });
    }
}
