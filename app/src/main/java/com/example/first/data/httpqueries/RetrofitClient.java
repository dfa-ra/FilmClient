package com.example.first.data.httpqueries;

import com.example.first.domain.interfaces.IRetrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements IRetrofit {

    private IAPI client;
    private final static String BaseURL = "https://kinopoiskapiunofficial.tech/";

    public RetrofitClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        client = retrofit.create(IAPI.class);

    }

    @Override
    public IAPI getApi() {
        return client;
    }
}
