package com.example.first.domain.usecase.outputUsecase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.example.first.R;
import com.example.first.domain.common.Md5Hash;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class GetFilmPoster {

    private final Context context;

    public GetFilmPoster(Context context) {
        this.context = context;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public Bitmap execute(String posterUrl) {
        if (posterUrl.isEmpty())
            return ((BitmapDrawable) context.getResources().getDrawable(R.drawable.moai, null)).getBitmap();

        Bitmap bitmap = null;
        try{
            bitmap = Picasso.get()
                    .load(posterUrl)
                    .get();
        } catch (Exception | OutOfMemoryError e) {
            Log.e("aa99", e.getMessage());
        }
        if (Md5Hash.getInstance().isPlug(bitmap)) {
            // Если это заглушка, возвращаем null или другой битмап по умолчанию
            return ((BitmapDrawable) context.getResources().getDrawable(R.drawable.moai, null)).getBitmap();
        }

        return bitmap;
    }

}
