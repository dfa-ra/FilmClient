package com.example.first.presentation.mainActivity.Fragments.favoritesFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.first.data.models.mainModel.FilmModel;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.injection.app.App;
import com.example.first.R;
import com.example.first.databinding.FragmentFavoritesBinding;
import com.example.first.presentation.mainActivity.Fragments.SendViewModel;
import com.example.first.presentation.mainActivity.Fragments.SendViewModelFactory;
import com.example.first.presentation.mainActivity.customBottomSheetDialog.CustomBottomSheetDialog;
import com.example.first.presentation.mainActivity.filmStrip.AdapterListener;
import com.example.first.presentation.mainActivity.filmStrip.ItemAdapter;

import javax.inject.Inject;

public class FavoritesFragment extends Fragment implements AdapterListener {

    FragmentFavoritesBinding binding;
    private final ItemAdapter adapter = new ItemAdapter(this);

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

        senderViewModel.getSelectedItem().observe(getViewLifecycleOwner(), item -> {
            favoritesViewModel.addToFavoritesList();
        });

        favoritesViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
            adapter.setItems(items);
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
        CustomBottomSheetDialog bottomSheetDialog = new CustomBottomSheetDialog(filmModel);
        bottomSheetDialog.show(getParentFragmentManager(), "CustomBottomSheetDialog");
        return true;
    }
}
