package com.example.first.filmStrip;

import android.view.View;

public interface AdapterListener{
    void onClick(FilmItem filmItem );
    boolean longOnClick(FilmItem filmItem );
}
