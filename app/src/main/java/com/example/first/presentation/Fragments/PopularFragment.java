package com.example.first.presentation.Fragments;

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

import com.example.first.domain.models.ShortFilmModel;
import com.example.first.presentation.DescriptionFilmActivity;
import com.example.first.R;
import com.example.first.databinding.FragmentPopularBinding;
import com.example.first.presentation.Fragments.ViewModel.FragmentsViewModel;
import com.example.first.presentation.filmStrip.AdapterListener;
import com.example.first.presentation.filmStrip.ItemAdapter;

public class PopularFragment extends Fragment implements AdapterListener {

    FragmentPopularBinding binding;
    ItemAdapter adapter = new ItemAdapter(this);
    FragmentsViewModel viewModel;

    public final static String Tag = "PopularFragmentTAG";

    private static PopularFragment fragment;

    public PopularFragment() {}

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
        viewModel = new ViewModelProvider(requireActivity()).get(FragmentsViewModel.class);
        viewModel.getItems().observe(getViewLifecycleOwner(), items -> {
            adapter.setItems(items);
        });

        Log.d(Tag, "FstInit");

        RecyclerView recyclerView = view.findViewById(binding.PopularRecyclerView.getId());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }


    @Override
    public void onClick(ShortFilmModel filmModel) {
        Log.i(Tag, "on click item");
        Intent intent = new Intent(getActivity(), DescriptionFilmActivity.class);
        intent.putExtra("filmModel", filmModel); //Optional parameters
        Log.i(Tag, "send data");
        startActivity(intent);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean longOnClick(ShortFilmModel filmModel) {
        Log.i(Tag, "Long click item");
        viewModel.selectItem(filmModel);
        return true;
    }
}