package com.example.first.injection.app;

import android.app.Application;

import com.example.first.injection.di.AppComponent;
import com.example.first.injection.di.AppModule;
import com.example.first.injection.di.DaggerAppComponent;


public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
