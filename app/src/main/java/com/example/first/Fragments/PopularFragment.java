package com.example.first.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.first.MainActivity;
import com.example.first.R;
import com.example.first.RequestFilm;
import com.example.first.RetrofitClient;
import com.example.first.databinding.ActivityMainBinding;
import com.example.first.databinding.FragmentPopularBinding;
import com.example.first.filmStrip.FilmItem;
import com.example.first.filmStrip.ItemAdapter;

import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PopularFragment extends Fragment {

    FragmentPopularBinding binding;
    ItemAdapter adapter = new ItemAdapter();

    public final static String Tag = "PopularFragmentTAG";
    private final static String BaseURL = "https://kinopoiskapiunofficial.tech/";


    private static PopularFragment fragment;

    public PopularFragment() {
        // Required empty public constructor
    }

    public static PopularFragment getInstance() {
        synchronized (FavoritesFragment.class) {
            if (fragment == null) {
                fragment = new PopularFragment();
            }
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        binding = FragmentPopularBinding.inflate(getLayoutInflater());
        Log.d(Tag, "FstInit");
        RecyclerView recyclerView = view.findViewById(binding.PopularRecyclerView.getId());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initFilmList();
        // Inflate the layout for this fragment
        return view;
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
                        public void onError(Throwable e) {
                            Log.e(Tag, Objects.requireNonNull(e.getMessage()));
                        }

                        @Override
                        public void onNext(FilmItem filmData) {
                            adapter.addItems(filmData);
                            Log.i(Tag, filmData.nameRu);
                        }
                        @Override
                        public void onComplete() {
//                            Log.i(Tag, "onComplete");
                        }
                    });
        }
    }
}