package com.example.first.injection.di;

import com.example.first.data.httpqueries.IRetrofit;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationById;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByName;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationById;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;
import com.example.first.domain.usecase.outputUsecase.AllToShortFilmsInformation;

import dagger.Module;
import dagger.Provides;

@Module
public class DomainModule {


    @Provides
    GetShortInformationAboutFilmsDb provideGetShortInformationAboutFilmsDb(){
        return new GetShortInformationAboutFilmsDb();
    }

    @Provides
    GetFilmInformationById provideGetFilmInformationById(IRetrofit requests){
        return new GetFilmInformationById(requests);
    }

    @Provides
    AllToShortFilmsInformation provideGetShortInformationAboutFilmsLocal(){
        return new AllToShortFilmsInformation();
    }

    @Provides
    GetFilmInformationByName provideGetFilmsInformationByName(IRetrofit requests, GetFilmInformationById getFilmInformationById) {
        return new GetFilmInformationByName(requests, getFilmInformationById);
    }

    @Provides
    GetFilmInformationByCollection provideGetFilmInformationByCollection(IRetrofit requests, GetFilmInformationById getFilmInformationById) {
        return new GetFilmInformationByCollection(requests, getFilmInformationById);
    }

    @Provides
    GetLongFilmInformationById provideGetLongFilmInformationById() {
        return new GetLongFilmInformationById();
    }


}
