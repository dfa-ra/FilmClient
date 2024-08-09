package com.example.first.injection.di;

import com.example.first.presentation.Fragments.favoritesFragment.FavoritesFragment;
import com.example.first.presentation.Fragments.mainFragment.MainFragment;
import com.example.first.presentation.MainActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, DomainModule.class, DataModule.class})
public interface AppComponent {
    void inject(MainFragment mainFragment);
    void inject(FavoritesFragment favoritesFragment);
}
