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
import com.example.first.databinding.FragmentFavoritesBinding;
import com.example.first.databinding.FragmentPopularBinding;
import com.example.first.filmStrip.AdapterListener;
import com.example.first.filmStrip.FilmItem;
import com.example.first.filmStrip.ItemAdapter;

public class FavoritesFragment extends Fragment implements AdapterListener {

    FragmentFavoritesBinding binding;
    ItemAdapter adapter = new ItemAdapter(this);
    SharedViewModel model;

    public final static String Tag = "FavoritesFragmentTAG";

    private static volatile FavoritesFragment fragment = null;

    public FavoritesFragment() {
        // Required empty public constructor
    }

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(Tag, "onCreateView");
        Log.i(MainActivity.Tag, "FavoritesFragment");
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        binding = FragmentFavoritesBinding.inflate(getLayoutInflater());
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        Log.d(Tag, "FstInit");
        RecyclerView recyclerView = view.findViewById(binding.FavoriteRecyclerView.getId());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d(Tag, "FstInit2");

        model.getSelectedItem().observe(getViewLifecycleOwner(), item -> {
            adapter.addItems(item);
            Log.d(Tag, "add new item");
        });

        Log.d(Tag, "FstInit3");
        return view;
    }

    @Override
    public void onClick(FilmItem filmItem) {

    }

    @Override
    public boolean longOnClick(FilmItem filmItem) {
        return true;
    }
}
