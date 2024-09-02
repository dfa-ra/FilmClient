package com.example.first.domain.usecase.outputUsecase;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.interfaces.ILocalDB;
import com.example.first.domain.models.LongFilmModel;

public class GetLongFilmInformationByIdFromLocal {
    public final ILocalDB iLocalDB;

    public GetLongFilmInformationByIdFromLocal(ILocalDB iLocalDB){
        this.iLocalDB = iLocalDB;
    }

    public LongFilmModel execute(int id){
        FilmModel model = iLocalDB.getFilmById(id);

        return new LongFilmModel(
                            model.kinopoiskId,
                            model.nameRu,
                            model.description,
                            model.countries,
                            model.genres,
                            model.ratingKinopoisk,
                            model.ratingImdb,
                            model.posterUrl,
                            model.comment,
                            model.poster);
    }
}
