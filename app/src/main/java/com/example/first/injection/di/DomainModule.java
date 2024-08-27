package com.example.first.injection.di;

import android.content.Context;

import com.example.first.domain.interfaces.IDbQueries;
import com.example.first.domain.interfaces.IRetrofit;
import com.example.first.domain.usecase.logicsUsecase.DeleteFilmById;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationById;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByName;
import com.example.first.domain.usecase.logicsUsecase.SelectedFilmToFavorites;
import com.example.first.domain.usecase.outputUsecase.GetFilmPoster;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationById;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;
import com.example.first.domain.usecase.outputUsecase.AllToShortFilmsInformation;

import dagger.Module;
import dagger.Provides;
import retrofit2.http.POST;

@Module
public class DomainModule {


    @Provides
    GetShortInformationAboutFilmsDb provideGetShortInformationAboutFilmsDb(IDbQueries dbQueries){
        return new GetShortInformationAboutFilmsDb(dbQueries);
    }

    @Provides
    GetFilmInformationById provideGetFilmInformationById(IRetrofit requests, GetFilmPoster getFilmPoster){
        return new GetFilmInformationById(requests, getFilmPoster);
    }

    @Provides
    AllToShortFilmsInformation provideGetShortInformationAboutFilmsLocal(){
        return new AllToShortFilmsInformation();
    }

    @Provides
    GetFilmInformationByName provideGetFilmsInformationByName(IRetrofit requests, GetFilmInformationById getFilmInformationById, IDbQueries dbQueries) {
        return new GetFilmInformationByName(requests, getFilmInformationById, dbQueries);
    }

    @Provides
    GetFilmInformationByCollection provideGetFilmInformationByCollection(IRetrofit requests, GetFilmInformationById getFilmInformationById, IDbQueries dbQueries) {
        return new GetFilmInformationByCollection(requests, getFilmInformationById, dbQueries);
    }

    @Provides
    GetLongFilmInformationById provideGetLongFilmInformationById(IDbQueries dbQueries) {
        return new GetLongFilmInformationById(dbQueries);
    }

    @Provides
    SelectedFilmToFavorites provideSelectedFilmToFavorites(IDbQueries dbQueries) {
        return new SelectedFilmToFavorites(dbQueries);
    }

    @Provides
    GetFilmPoster provideGetFilmPoster(Context context){
        return new GetFilmPoster(context);
    }

    @Provides
    DeleteFilmById provideDeleteFilmById(IDbQueries dbQueries){ return new DeleteFilmById(dbQueries);}
}
