package com.example.first.presentation.Fragments.favoritesFragment;

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

import com.example.first.data.dbqueries.DbQueries;
import com.example.first.domain.interfaces.DataFetchCallback;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.presentation.Fragments.SendViewModel;
import com.example.first.presentation.MainActivity;
import com.example.first.R;
import com.example.first.databinding.FragmentFavoritesBinding;
import com.example.first.presentation.filmStrip.AdapterListener;
import com.example.first.presentation.filmStrip.ItemAdapter;

public class FavoritesFragment extends Fragment implements AdapterListener {

    FragmentFavoritesBinding binding;
    private final ItemAdapter adapter = new ItemAdapter(this);

    SendViewModel sendViewModel;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        binding = FragmentFavoritesBinding.inflate(getLayoutInflater());
        favoritesViewModel = new ViewModelProvider(requireActivity(), new FavoritesViewModelFactory()).get(FavoritesViewModel.class);
        sendViewModel = new ViewModelProvider(requireActivity()).get(SendViewModel.class);

        favoritesViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
            adapter.setItems(items);
        });

        sendViewModel.getSelectedItem().observe(getViewLifecycleOwner(), item -> {
            favoritesViewModel.addToFavoritesList(item);
        });

        RecyclerView recyclerView = view.findViewById(binding.FavoriteRecyclerView.getId());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
