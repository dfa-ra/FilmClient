package com.example.first.domain.models;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class ShortFilmModel implements Serializable{
    public int kinopoiskId;
    public String nameRu;
    public String ratingKinopoisk;
    public String ratingImdb;
    public String genre;
    public boolean isChecked = false;
}
