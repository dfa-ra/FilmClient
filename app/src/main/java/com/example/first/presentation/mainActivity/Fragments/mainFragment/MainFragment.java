package com.example.first.presentation.mainActivity.Fragments.mainFragment;


import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.example.first.databinding.FragmentMainBinding;
import com.example.first.domain.common.enums.CollectionType;
import com.example.first.domain.models.LongFilmModel;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.injection.app.App;
import com.example.first.presentation.descriptionActivity.DescriptionFilmActivity;
import com.example.first.R;
import com.example.first.presentation.mainActivity.Fragments.SendViewModel;
import com.example.first.presentation.mainActivity.Fragments.SendViewModelFactory;
import com.example.first.presentation.mainActivity.Fragments.favoritesFragment.FavoritesFragment;
import com.example.first.presentation.mainActivity.filmStrip.AdapterListener;
import com.example.first.presentation.mainActivity.filmStrip.ItemAdapter;

import javax.inject.Inject;

public class MainFragment extends Fragment implements AdapterListener {

    FragmentMainBinding binding;
    ItemAdapter adapter = new ItemAdapter(this);

    @Inject
    MainViewModelFactory mainViewModelFactory;

    @Inject
    SendViewModelFactory sendViewModelFactory;

    SendViewModel senderViewModel;
    MainViewModel mainViewModel;

    public final static String Tag = "PopularFragmentTAG";

    private static MainFragment fragment;

    public MainFragment() {}

    public static MainFragment getInstance() {
        synchronized (FavoritesFragment.class) {
            if (fragment == null) {
                fragment = new MainFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        binding = FragmentMainBinding.inflate(getLayoutInflater());

        senderViewModel = new ViewModelProvider(requireActivity(), sendViewModelFactory).get(SendViewModel.class);
        mainViewModel = new ViewModelProvider(requireActivity(), mainViewModelFactory).get(MainViewModel.class);

        mainViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
            adapter.setItems(items);
        });

        RecyclerView recyclerView = view.findViewById(binding.PopularRecyclerView.getId());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onClick(ShortFilmModel filmModel) {
        Intent intent = new Intent(getActivity(), DescriptionFilmActivity.class);
        LongFilmModel model = mainViewModel.getLongFilmModel(filmModel);
        intent.putExtra("filmModel", model); //Optional parameters
        startActivity(intent);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean longOnClick(ShortFilmModel filmModel) {
        if (!senderViewModel.selectItem(filmModel))
            Toast.makeText(getContext(), "Данный элемент уже находится в избранное", Toast.LENGTH_SHORT).show();
        return true;
    }

    public void searchFilmByName(String name) {
        mainViewModel.searchFilmByName(name);
    }

    public void searchFilmCollection(String name) {
        mainViewModel.searchFilmByCollection(CollectionType.valueOf(name));
    }



}