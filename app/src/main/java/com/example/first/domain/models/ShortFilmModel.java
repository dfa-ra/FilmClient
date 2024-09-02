package com.example.first.domain.models;

import android.graphics.Bitmap;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class ShortFilmModel implements Serializable{
    public int kinopoiskId;
    public String nameRu = "";
    public String ratingKinopoisk = "";
    public String ratingImdb = "";
    public String genre = "";
    public String posterUrlPreview = "";
    public boolean isChecked = false;
    public boolean isReadable = false;
    public Bitmap posterPreview = null;
}
