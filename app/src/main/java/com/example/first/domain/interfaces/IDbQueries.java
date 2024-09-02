package com.example.first.domain.interfaces;

import android.util.Log;

import com.example.first.data.dbqueries.dbroom.AppDatabase;

public interface IDbQueries {
    AppDatabase getAppDatabase();
}
