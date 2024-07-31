package com.example.first.domain.usecase;

import android.util.Log;

import com.example.first.data.FilmsRepositoryImpl;
import com.example.first.domain.repository.FilmsRepository;

public class GetShortFilmsInformationByNameUseCase {
    private final FilmsRepository filmsRepository;

    public GetShortFilmsInformationByNameUseCase(FilmsRepository filmsRepository){
        this.filmsRepository = filmsRepository;
    }

    public void execute(String name, int page){
        Log.i(FilmsRepositoryImpl.Tag, "search in Usecase");
        filmsRepository.getShortFilmsInformationByName(name, page);
    }
}
