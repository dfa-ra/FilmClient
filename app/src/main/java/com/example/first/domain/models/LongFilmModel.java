package com.example.first.domain.models;

import android.graphics.Bitmap;

import com.example.first.data.models.mainModel.Country;
import com.example.first.data.models.mainModel.Genre;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class LongFilmModel implements Serializable{
    public int kinopoiskId;
    public String nameRu = "";
    public String description = "";
    public List<Country> countries = null;
    public List<Genre> genres = null;
    public String ratingKinopoisk = "";
    public String ratingImdb = "";
    public String posterUrl = "";
    public String comment = "";
    public Bitmap poster = null;
}
