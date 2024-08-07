package com.example.first.injection.di;

import com.example.first.domain.interfaces.DataFetchCallback;
import com.example.first.domain.interfaces.Requests;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationById;
import com.example.first.domain.usecase.logicsUsecase.GetFilmsInformationByName;
import com.example.first.domain.usecase.logicsUsecase.SetFilmInformationToDb;
import com.example.first.domain.usecase.outputUsecase.GetShortFilmInformationById;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsLocal;

import dagger.Module;

@Module
public class DomainModule {

    public SetFilmInformationToDb provideSetFilmInformationToDb(ShortFilmModel shortFilmModel){
        return new SetFilmInformationToDb();
    }

    public GetShortInformationAboutFilmsDb provideGetShortInformationAboutFilmsDb(){
        return new GetShortInformationAboutFilmsDb();
    }

    public GetFilmInformationById provideGetFilmInformationById(Requests requests, DataFetchCallback callback){
        return new GetFilmInformationById(requests, callback);
    }

    public GetShortInformationAboutFilmsLocal provideGetShortInformationAboutFilmsLocal(){
        return new GetShortInformationAboutFilmsLocal();
    }

    public GetFilmsInformationByName provideGetFilmsInformationByName(Requests requests, GetFilmInformationById getFilmInformationById, DataFetchCallback callback){
        return new GetFilmsInformationByName(requests, getFilmInformationById, callback);
    }

    public GetFilmInformationByCollection provideGetFilmInformationByCollection(Requests requests, GetFilmInformationById getFilmInformationById, DataFetchCallback callback){
        return new GetFilmInformationByCollection(requests, getFilmInformationById, callback);
    }

}
