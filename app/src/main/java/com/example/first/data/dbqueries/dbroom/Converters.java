package com.example.first.data.dbqueries.dbroom;

import androidx.room.TypeConverter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

import com.example.first.data.models.mainModel.Country;
import com.example.first.data.models.mainModel.Genre;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Country> fromStringToCountryList(String value) {
        Type listType = new TypeToken<List<Country>>() {}.getType();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String fromCountryListToString(List<Country> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<Genre> fromStringToGenreList(String value) {
        Type listType = new TypeToken<List<Genre>>() {}.getType();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String fromGenreListToString(List<Genre> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static byte[] fromBitmapToByteArray(Bitmap bitmap) {
        if (bitmap == null) {
            return null; // или return new byte[0]; если вы хотите вернуть пустой массив байтов
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }

    @TypeConverter
    public static Bitmap fromByteArrayToBitmap(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null; // или вернуть какой-то другой Bitmap, например, изображение по умолчанию
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
