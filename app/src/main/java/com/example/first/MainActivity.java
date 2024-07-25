package com.example.first;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.first.Fragments.FavoritesFragment;
import com.example.first.Fragments.PopularFragment;
import com.example.first.databinding.ActivityMainBinding;
import com.example.first.filmStrip.AdapterListener;
import com.example.first.filmStrip.FilmItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;
    private boolean showingPopular = true;
    public final static String Tag = "MoyaProgamma";

    @SuppressLint({"CheckResult", "SetTextI18n", "MissingInflatedId", "CommitTransaction"})
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

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(binding.Fragments.getId(), PopularFragment.getInstance(), PopularFragment.Tag);
        fragmentTransaction.add(binding.Fragments.getId(), FavoritesFragment.getInstance(), FavoritesFragment.Tag);
        fragmentTransaction.hide(FavoritesFragment.getInstance()); // Скрываем второй фрагмент
        fragmentTransaction.commit();

        binding.popularButton.setOnClickListener(view -> {

            highlightButton(binding.popularButton);

            FragmentManager fragmentManager2 = getSupportFragmentManager();
            fragmentManager2.beginTransaction()
                    .hide(FavoritesFragment.getInstance())
                    .show(PopularFragment.getInstance())
                    .commit();
        });

        binding.favoritesButton.setOnClickListener(view -> {

            highlightButton(binding.favoritesButton);

            FragmentManager fragmentManager3 = getSupportFragmentManager();
            fragmentManager3.beginTransaction()
                    .hide(PopularFragment.getInstance())
                    .show(FavoritesFragment.getInstance())
                    .commit();
        });
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

    private void highlightButton(Button button) {
        binding.favoritesButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.holo_blue_light));
        binding.popularButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.holo_blue_light));
        button.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.holo_blue_dark));
    }
}