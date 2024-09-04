package com.example.first.presentation.mainActivity.Fragments.favoritesFragment;

import com.example.first.domain.models.ShortFilmModel;

public interface AdapterListener{
    void onClick(ShortFilmModel filmModel);
    void showTrash(boolean flag);
    void deleteFilm(ShortFilmModel filmModel);
    void updateIsReadable(int id, boolean isReadable);
    void updateComment(int id, boolean isReadable);
}
