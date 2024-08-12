package com.example.first.data.models;

import com.example.first.data.models.mainModel.FilmModel;

import java.io.Serializable;
import java.util.List;


public class KeywordCollectionModel implements Serializable {

    public String keyword;
    public int pagesCount;
    public List<FilmModel> films;
    public int searchFilmsCountResult;

}
