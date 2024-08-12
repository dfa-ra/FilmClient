package com.example.first.injection.di;

import com.example.first.presentation.descriptionActivity.DescriptionFilmActivity;
import com.example.first.presentation.mainActivity.Fragments.favoritesFragment.FavoritesFragment;
import com.example.first.presentation.mainActivity.Fragments.mainFragment.MainFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, DomainModule.class, DataModule.class})
public interface AppComponent {
    void inject(MainFragment mainFragment);
    void inject(FavoritesFragment favoritesFragment);
    void inject(DescriptionFilmActivity descriptionFilmActivity);
}
