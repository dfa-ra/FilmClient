package com.example.first;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.first.databinding.ActivityDescriptionFilmBinding;
import com.example.first.filmStrip.FilmItem;

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
        FilmItem filmItem = (FilmItem) getIntent().getSerializableExtra("filmItem");
        if (filmItem != null) {
            binding.filmName.setText(filmItem.nameRu);
            binding.descriptionFilm.setText(filmItem.description);
            StringBuilder text = new StringBuilder();
            for (FilmItem.Genre genre : filmItem.genres){
                text.append(genre.genre);
                text.append(", ");
            }
            binding.genreFilm.setText(text);
            binding.ratingIMDBFilm.setText(filmItem.ratingImdb);
            binding.ratingKinoPoiskFilm.setText(filmItem.ratingKinopoisk);
            StringBuilder text2 = new StringBuilder();
            for (FilmItem.Country country : filmItem.countries){
                text2.append(country.country);
                text2.append(", ");
            }
            binding.countriesFilm.setText(text2);
        }
    }
}