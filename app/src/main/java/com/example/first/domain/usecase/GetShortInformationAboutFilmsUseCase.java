package com.example.first.domain.usecase;

import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.repository.FilmsRepository;

import java.util.List;

public class GetShortInformationAboutFilmsUseCase {
    private final FilmsRepository filmsRepository;

    public GetShortInformationAboutFilmsUseCase(FilmsRepository filmsRepository){
        this.filmsRepository = filmsRepository;
    }

    public List<ShortFilmModel> execute(){
        return filmsRepository.getAllShortFilmsInformation();
    }
}
