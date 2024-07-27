package com.example.first.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.first.DescriptionFilmActivity;
import com.example.first.R;
import com.example.first.RequestFilm;
import com.example.first.RetrofitClient;
import com.example.first.databinding.FragmentPopularBinding;
import com.example.first.filmStrip.AdapterListener;
import com.example.first.filmStrip.FilmItem;
import com.example.first.filmStrip.ItemAdapter;

import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PopularFragment extends Fragment implements AdapterListener {

    FragmentPopularBinding binding;
    ItemAdapter adapter = new ItemAdapter(this);
    SharedViewModel viewModel;

    public final static String Tag = "PopularFragmentTAG";
    private final static String BaseURL = "https://kinopoiskapiunofficial.tech/";

    private static PopularFragment fragment;

    public PopularFragment() {

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
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getItems().observe(getViewLifecycleOwner(), items -> {
            adapter.setItems(items);
        });

        Log.d(Tag, "FstInit");

        RecyclerView recyclerView = view.findViewById(binding.PopularRecyclerView.getId());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        Log.d(Tag, "FstInit2");
        initFilmList();

        Log.d(Tag, "FstInit3");
        // Inflate the layout for this fragment
        return view;
    }


    @SuppressLint({"CheckResult", "NotifyDataSetChanged"})
    private void initFilmList(){
        Retrofit retrofit = RetrofitClient.getClient(BaseURL);
        RequestFilm requestFilm = retrofit.create(RequestFilm.class);

        Observable.range(1, 20)
                .flatMap(number -> requestFilm.getFilmById(300 + number).
                        subscribeOn(Schedulers.io())
                        .onErrorResumeNext(throwable -> Observable.empty())
                )
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        items -> viewModel.setItems(items),
                        throwable -> Log.e(Tag, String.valueOf(throwable))
                );
    }

    @Override
    public void onClick(FilmItem filmItem) {
        Log.i(Tag, "on click item");
        Intent intent = new Intent(getActivity(), DescriptionFilmActivity.class);
        intent.putExtra("filmItem", filmItem); //Optional parameters
        Log.i(Tag, "send data");
        startActivity(intent);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean longOnClick(FilmItem filmItem) {
        Log.i(Tag, "Long click item");
        viewModel.selectItem(filmItem);
        return true;
    }
}