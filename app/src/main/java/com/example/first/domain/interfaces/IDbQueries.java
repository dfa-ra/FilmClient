package com.example.first.domain.interfaces;

import android.util.Log;

import com.example.first.data.dbqueries.dbroom.AppDatabase;
import com.example.first.data.dbqueries.dbroom.Dao;
import com.example.first.data.models.mainModel.FilmModel;

import java.util.ArrayList;
import java.util.List;

public interface IDbQueries {
    AppDatabase getAppDatabase();
}
