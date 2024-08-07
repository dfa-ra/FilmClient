package com.example.first.domain.models;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LongFilmModel {
    public int kinopoiskId;
    public String nameRu;
    public List<com.example.first.data.models.FilmModel.Country> countries;
    public List<com.example.first.data.models.FilmModel.Genre> genres;
    public String ratingKinopoisk;
    public String ratingImdb;
    public String posterUrl;
    public String posterUrlPreview;

    public class Genre implements Serializable{
        public String genre;
    }
    public class Country implements Serializable{
        public String country;
    }
}
