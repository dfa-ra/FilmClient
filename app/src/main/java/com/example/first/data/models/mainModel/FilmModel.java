package com.example.first.data.models.mainModel;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.first.data.common.enums.CollectionType;
import com.example.first.data.dbqueries.dbroom.Converters;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity(tableName = "films")
public class FilmModel {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int kinopoiskId;
    public int filmId;
    public String imdbId = "";
    public String nameRu = "";
    public String nameEn = "";
    public String nameOriginal = "";

    @TypeConverters(Converters.class)
    public List<Country> countries = null;

    @TypeConverters(Converters.class)
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
    public boolean isReadable = false;
    public String comment = "";

    public CollectionType collection;

    @TypeConverters(Converters.class)
    public Bitmap posterPreview;

    @TypeConverters(Converters.class)
    public Bitmap poster = null;
}

