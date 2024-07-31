package com.example.first.data.models;

import java.io.Serializable;
import java.util.List;

import io.reactivex.rxjava3.core.Scheduler;

public class KeywordCollectionModel implements Serializable {

    public String keyword;
    public int pagesCount;
    public List<FilmModel> films;
    public int searchFilmsCountResult;

}
