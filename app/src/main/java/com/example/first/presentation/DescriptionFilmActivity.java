package com.example.first.presentation;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.first.R;
import com.example.first.data.models.mainModel.Country;
import com.example.first.data.models.mainModel.Genre;
import com.example.first.databinding.ActivityDescriptionFilmBinding;
import com.example.first.domain.models.LongFilmModel;
import com.example.first.domain.models.ShortFilmModel;

import java.util.Objects;

public class DescriptionFilmActivity extends AppCompatActivity {

    public static final String Tag = "DescriptionFilmActivityTag";
    private ActivityDescriptionFilmBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDescriptionFilmBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.i(Tag, "start");
        LongFilmModel filmModel = (LongFilmModel) getIntent().getSerializableExtra("filmModel");
        if (filmModel != null) {

            if (!Objects.equals(filmModel.posterUrl, ""))
                Glide.with(this)
                        .load(filmModel.posterUrl)
                        .into(binding.imageView);
            else
                Glide.with(this)
                        .load("")
                        .into(binding.imageView);

            binding.filmName.setText(filmModel.nameRu);
            binding.descriptionFilm.setText(filmModel.description);
            StringBuilder text = new StringBuilder();
            for (Genre genre : filmModel.genres){
                text.append(genre.genre);
                text.append(", ");
            }
            binding.genreFilm.setText(text);
            binding.ratingIMDBFilm.setText(filmModel.ratingImdb);
            binding.ratingKinoPoiskFilm.setText(filmModel.ratingKinopoisk);
            StringBuilder text2 = new StringBuilder();
            for (Country country : filmModel.countries){
                text2.append(country.country);
                text2.append(", ");
            }
            binding.countriesFilm.setText(text2);
        }
    }
}