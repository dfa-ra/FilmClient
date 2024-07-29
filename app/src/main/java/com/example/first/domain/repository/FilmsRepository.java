package com.example.first.domain.repository;

import com.example.first.data.models.FilmModel;
import com.example.first.domain.models.ShortFilmModel;

import java.util.List;

public interface FilmsRepository {
    ShortFilmModel getShortFilmInformationById(int id);
    FilmModel getFilmInformationById(int id);
    List<ShortFilmModel> getAllShortFilmsInformation();
}
