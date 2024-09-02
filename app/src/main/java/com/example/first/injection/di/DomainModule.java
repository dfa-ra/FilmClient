package com.example.first.injection.di;

import android.content.Context;

import com.example.first.domain.interfaces.IDbQueries;
import com.example.first.domain.interfaces.ILocalDB;
import com.example.first.domain.interfaces.IRetrofit;
import com.example.first.domain.usecase.dbUsecase.DeleteFilmByIdFromBd;
import com.example.first.domain.usecase.dbUsecase.GetFilmByIdFromDb;
import com.example.first.domain.usecase.dbUsecase.GetFilmsFromDb;
import com.example.first.domain.usecase.dbUsecase.SetFilmToDb;
import com.example.first.domain.usecase.dbUsecase.UpdateComment;
import com.example.first.domain.usecase.dbUsecase.UpdateIsReadable;
import com.example.first.domain.usecase.logicsUsecase.FilmModelToShortModel;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByCollection;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationById;
import com.example.first.domain.usecase.logicsUsecase.GetFilmInformationByName;
import com.example.first.domain.usecase.logicsUsecase.MergeFlowFromDbAndApi;
import com.example.first.domain.usecase.logicsUsecase.SelectedFilmToFavorites;
import com.example.first.domain.usecase.outputUsecase.GetFilmPoster;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromBd;
import com.example.first.domain.usecase.outputUsecase.GetLongFilmInformationByIdFromLocal;
import com.example.first.domain.usecase.outputUsecase.GetShortInformationAboutFilmsDb;
import com.example.first.domain.usecase.outputUsecase.AllToShortFilmsInformation;

import dagger.Module;
import dagger.Provides;

@Module
public class DomainModule {

    @Provides
    MergeFlowFromDbAndApi provideMergeFlowFromDbAndApi(){
        return new MergeFlowFromDbAndApi();
    }

    @Provides
    GetLongFilmInformationByIdFromLocal provideGetLongFilmInformationByIdFromLocal(ILocalDB iLocalDB){
        return new GetLongFilmInformationByIdFromLocal(iLocalDB);
    }

    @Provides
    UpdateIsReadable provideUpdateIsReadable(IDbQueries iDbQueries){
        return new UpdateIsReadable(iDbQueries);
    }

    @Provides
    UpdateComment provideUpdateComment(IDbQueries iDbQueries){
        return new UpdateComment(iDbQueries);
    }

    @Provides
    GetFilmsFromDb provideGetFilmsFromDb(IDbQueries iDbQueries){
        return new GetFilmsFromDb(iDbQueries);
    }

    @Provides
    GetFilmByIdFromDb provideGetFilmByIdFromDb(IDbQueries iDbQueries){
        return new GetFilmByIdFromDb(iDbQueries);
    }

    @Provides
    SetFilmToDb provideSetFilmToDb(IDbQueries iDbQueries){
        return new SetFilmToDb(iDbQueries);
    }

    @Provides
    DeleteFilmByIdFromBd provideDeleteFilmByIdFromBd(IDbQueries iDbQueries){
        return new DeleteFilmByIdFromBd(iDbQueries);
    }

    @Provides
    GetShortInformationAboutFilmsDb provideGetShortInformationAboutFilmsDb(GetFilmsFromDb getFilmsFromDb){
        return new GetShortInformationAboutFilmsDb(getFilmsFromDb);
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
    GetFilmInformationByName provideGetFilmsInformationByName(IRetrofit requests, GetFilmInformationById getFilmInformationById, FilmModelToShortModel filmModelToShortModel, ILocalDB iLocalDB) {
        return new GetFilmInformationByName(requests, getFilmInformationById, filmModelToShortModel, iLocalDB);
    }

    @Provides
    GetFilmInformationByCollection provideGetFilmInformationByCollection(IRetrofit requests, GetFilmInformationById getFilmInformationById, FilmModelToShortModel filmModelToShortModel, ILocalDB iLocalDB) {
        return new GetFilmInformationByCollection(requests, getFilmInformationById, filmModelToShortModel, iLocalDB);
    }

    @Provides
    GetLongFilmInformationByIdFromBd provideGetLongFilmInformationById(GetFilmByIdFromDb getFilmByIdFromDb) {
        return new GetLongFilmInformationByIdFromBd(getFilmByIdFromDb);
    }

    @Provides
    SelectedFilmToFavorites provideSelectedFilmToFavorites(SetFilmToDb setFilmToDb, ILocalDB iLocalDB, GetFilmPoster getFilmPoster) {
        return new SelectedFilmToFavorites(setFilmToDb, iLocalDB, getFilmPoster);
    }

    @Provides
    GetFilmPoster provideGetFilmPoster(Context context){
        return new GetFilmPoster(context);
    }

    @Provides
    FilmModelToShortModel provideFilmModelToShortModel(){
        return new FilmModelToShortModel();
    }
}
