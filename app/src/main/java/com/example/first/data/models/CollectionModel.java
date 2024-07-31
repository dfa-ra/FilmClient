package com.example.first.data.models;

import java.io.Serializable;
import java.util.List;

public class CollectionModel implements Serializable {
    public int total;
    public int totalPages;
    public List<FilmModel> items;
}
