package com.example.first.data.models;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FilmModel implements Serializable {
    public int kinopoiskId;
    public int filmId;
    public String imdbId = "";
    public String nameRu = "";
    public String nameEn = "";
    public String nameOriginal = "";
    public List<Country> countries = null;
    public List<Genre> genres = null;
    public String ratingKinopoisk = "";
    public String ratingImdb = "";
    public int year;
    public String type = "";
    public String posterUrl = "";
    public String posterUrlPreview = "";
    public String coverUrl = "";
    public String logoUrl = "";
    public String description = "";
    public String ratingAgeLimits = "";
    public boolean isChecked = false;

    public class Genre implements Serializable{
        public String genre;
    }
    public class Country implements Serializable{
        public String country;
    }
}

