package com.example.first.presentation.descriptionActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.first.R;
import com.example.first.data.models.mainModel.Country;
import com.example.first.data.models.mainModel.Genre;
import com.example.first.databinding.ActivityDescriptionFilmBinding;
import com.example.first.domain.models.LongFilmModel;
import com.example.first.injection.app.App;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import javax.inject.Inject;

public class DescriptionFilmActivity extends AppCompatActivity {

    public static final String Tag = "DescriptionFilmActivityTag";

    private ActivityDescriptionFilmBinding binding;

    @Inject
    DescriptionViewModelFactory descriptionViewModelFactory;

    DescriptionViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDescriptionFilmBinding.inflate(getLayoutInflater());

        ((App) getApplication()).getAppComponent().inject(this);

        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        vm = new ViewModelProvider(this, descriptionViewModelFactory).get(DescriptionViewModel.class);

        Log.i(Tag, "start");
        Integer kinopoiskId = (Integer) getIntent().getSerializableExtra("filmModel");

        vm.getLongFilmModelLocal(kinopoiskId).thenAccept(
                filmModel -> {
                    if (filmModel != null) {
                        try {
                            if (filmModel.poster == null)
                                Executors.newSingleThreadExecutor().submit(() -> {
                                    Bitmap bitmap = vm.getPoster(filmModel.posterUrl);
                                    binding.imageView.setImageBitmap(bitmap);
                                });
                            else binding.imageView.setImageBitmap(filmModel.poster);

                            if (!filmModel.nameRu.isEmpty()) binding.filmName.setText(filmModel.nameRu);
                            if (!filmModel.description.isEmpty())
                                binding.descriptionFilm.setText(filmModel.description);
                            if (!filmModel.genres.isEmpty()) {
                                StringBuilder text = new StringBuilder();
                                for (Genre genre : filmModel.genres) {
                                    text.append(genre.genre);
                                    text.append(", ");
                                }
                                binding.genreFilm.setText(text);
                            }
                            if (!filmModel.ratingImdb.isEmpty())
                                binding.ratingIMDBFilm.setText(filmModel.ratingImdb);
                            if (!filmModel.ratingKinopoisk.isEmpty())
                                binding.ratingKinoPoiskFilm.setText(filmModel.ratingKinopoisk);
                            if (!filmModel.countries.isEmpty()) {
                                StringBuilder text2 = new StringBuilder();
                                for (Country country : filmModel.countries) {
                                    text2.append(country.country);
                                    text2.append(", ");
                                }
                                binding.countriesFilm.setText(text2);
                            }
                            if (!Objects.equals(filmModel.comment, "")){
                                binding.commentLayout.setVisibility(View.VISIBLE);
                                binding.yourComment.setText(filmModel.comment);
                            }
                        }catch (Exception e){
                            Log.e("aa99", e.getMessage());
                        }
                    }
                }
        );
    }
}