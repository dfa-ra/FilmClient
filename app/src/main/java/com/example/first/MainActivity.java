package com.example.first;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.first.databinding.ActivityMainBinding;
import com.example.first.filmStrip.FilmItem;
import com.example.first.filmStrip.ItemAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    //https://kinopoiskapiunofficial.tech/
    ActivityMainBinding binding;
    private final static String Tag = "MoyaProgamma";
    private final static String BaseURL = "https://kinopoiskapiunofficial.tech/";
    ItemAdapter adapter = new ItemAdapter();

    @SuppressLint({"CheckResult", "SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.i(Tag, "onCreate");
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        initFilmList();
    }

    @Override
    protected void onStart() {
        Log.i(Tag, "onStart");
        super.onStart();


        Disposable disposable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        tick -> updateTime(),
                        throwable -> Log.e(Tag, "Error in timer", throwable)
                );

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        binding.time.setText(currentTime);
    }

    private void initFilmList(){
        Retrofit retrofit = RetrofitClient.getClient(BaseURL);
        RequestFilm requestFilm = retrofit.create(RequestFilm.class);

        for (int i = 1; i <= 20; i++) {

            Observable<FilmItem> observable = requestFilm.getFilm(300+i);

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<FilmItem>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(FilmItem filmData) {
                            adapter.addItems(filmData);
                            Log.i(Tag, filmData.nameRu);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(Tag, Objects.requireNonNull(e.getMessage()));
                        }
                        @Override
                        public void onComplete() {
//                            Log.i(Tag, "onComplete");
                        }
                    });
        }
    }
}