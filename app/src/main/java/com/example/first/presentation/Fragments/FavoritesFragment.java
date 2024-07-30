package com.example.first.presentation.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.first.domain.models.ShortFilmModel;
import com.example.first.presentation.Fragments.ViewModel.FragmentsViewModel;
import com.example.first.presentation.MainActivity;
import com.example.first.R;
import com.example.first.databinding.FragmentFavoritesBinding;
import com.example.first.presentation.filmStrip.AdapterListener;
import com.example.first.presentation.filmStrip.ItemAdapter;

public class FavoritesFragment extends Fragment implements AdapterListener {

    FragmentFavoritesBinding binding;
    ItemAdapter adapter = new ItemAdapter(this);
    FragmentsViewModel model;

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
        model = new ViewModelProvider(requireActivity()).get(FragmentsViewModel.class);

        Log.d(Tag, "FstInit");

        RecyclerView recyclerView = view.findViewById(binding.FavoriteRecyclerView.getId());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.d(Tag, "FstInit2");

        model.getSelectedItem().observe(getViewLifecycleOwner(), item -> {
            if (!adapter.addItem(item))
                Toast.makeText(getContext(), "Данный элемент уже находится в избранное", Toast.LENGTH_SHORT).show();
        });

        Log.d(Tag, "FstInit3");

        return view;
    }

    @Override
    public void onClick(ShortFilmModel filmModel) {

    }

    @Override
    public boolean longOnClick(ShortFilmModel filmModel) {
        return true;
    }
}
