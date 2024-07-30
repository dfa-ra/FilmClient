package com.example.first.domain.usecase;

import com.example.first.domain.repository.FilmsRepository;

public class SelectedFilmToFavoritesUseCase {
    private final FilmsRepository filmsRepository;

    public SelectedFilmToFavoritesUseCase(FilmsRepository filmsRepository){
        this.filmsRepository = filmsRepository;
    }

    public void execute(int id){
        filmsRepository.selectedFilmToFavoritesUseCase(id);
    }
}
