package com.example.first.presentation.mainActivity.Fragments.favoritesFragment;

import android.os.Bundle;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.first.domain.models.ShortFilmModel;
import com.example.first.injection.app.App;
import com.example.first.R;
import com.example.first.databinding.FragmentFavoritesBinding;
import com.example.first.presentation.descriptionActivity.DescriptionFilmActivity;
import com.example.first.presentation.mainActivity.Fragments.Fragments;
import com.example.first.presentation.mainActivity.Fragments.MyMainFragment;
import com.example.first.presentation.mainActivity.Fragments.SendViewModel;
import com.example.first.presentation.mainActivity.Fragments.SendViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FavoritesFragment extends MyMainFragment implements AdapterListener {

    FragmentFavoritesBinding binding;
    private final ItemAdapter adapter = new ItemAdapter(this);

    private List<ShortFilmModel> selectedItems = new ArrayList<>();

    @Inject
    FavoritesViewModelFactory favoritesViewModelFactory;

    @Inject
    SendViewModelFactory sendViewModelFactory;

    SendViewModel senderViewModel;
    FavoritesViewModel favoritesViewModel;

    public final static String Tag = "FavoritesFragmentTAG";

    private static volatile FavoritesFragment fragment = null;

    public FavoritesFragment() {}

    public static FavoritesFragment getInstance() {
        if (fragment == null) {
            synchronized (FavoritesFragment.class) {
                if (fragment == null) {
                    fragment = new FavoritesFragment();
                }
            }
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getActivity() != null;
        ((App) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        binding = FragmentFavoritesBinding.inflate(getLayoutInflater());


        favoritesViewModel = new ViewModelProvider(requireActivity(), favoritesViewModelFactory).get(FavoritesViewModel.class);
        senderViewModel = new ViewModelProvider(requireActivity(), sendViewModelFactory).get(SendViewModel.class);

        favoritesViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
            Log.d("aa66", "update items");
            adapter.setItems(items);
        });

        binding.BtnDelete.setOnClickListener(view1 -> favoritesViewModel.deleteFilms(adapter.getSelectedItems()));

        RecyclerView recyclerView = view.findViewById(binding.FavoriteRecyclerView.getId());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        Log.d("aa66", "init");

        return view;
    }

    @Override
    public void onClick(ShortFilmModel filmModel) {
        Intent intent = new Intent(getActivity(), DescriptionFilmActivity.class);
        intent.putExtra("filmModel", filmModel.kinopoiskId);
        startActivity(intent);
    }

    @Override
    public void showTrash(boolean visible) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("aa--", "Flag: " + visible);
                binding.BtnDelete.setVisibility(visible ? View.VISIBLE : View.GONE);
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    Log.d("aa--", " Код выполняется в основном потоке");
                } else {
                    Log.d("aa--", " Код выполняется в другом потоке");
                }
            }
        });
    }


    @Override
    public void deleteFilm(ShortFilmModel filmModel) {
        favoritesViewModel.deleteFilm(filmModel);
    }

    @Override
    public void updateIsReadable(int id, boolean isReadable) {
        favoritesViewModel.updateIsReadable(id, isReadable);
    }

    @Override
    public void updateComment(int id, boolean isReadable) {

    }

    @Override
    public void searchFilmByCollection(String collectionType) {

    }

    @Override
    public void searchFilmByName(String name) {

    }
}
