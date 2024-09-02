package com.example.first.presentation.mainActivity.Fragments.mainFragment;

import com.example.first.domain.models.ShortFilmModel;

public interface AdapterListener{
    void onClick(ShortFilmModel filmModel);
    boolean longOnClick(ShortFilmModel filmModel);
}
