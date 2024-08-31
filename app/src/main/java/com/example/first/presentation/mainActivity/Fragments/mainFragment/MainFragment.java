package com.example.first.presentation.mainActivity.Fragments.mainFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.first.R;
import com.example.first.databinding.FragmentMainBinding;
import com.example.first.domain.common.enums.CollectionType;
import com.example.first.domain.models.LongFilmModel;
import com.example.first.domain.models.ShortFilmModel;
import com.example.first.injection.app.App;
import com.example.first.presentation.descriptionActivity.DescriptionFilmActivity;
import com.example.first.presentation.mainActivity.Fragments.SendViewModel;
import com.example.first.presentation.mainActivity.Fragments.SendViewModelFactory;
import com.example.first.presentation.mainActivity.Fragments.favoritesFragment.FavoritesFragment;
import com.example.first.presentation.mainActivity.filmStrip.AdapterListener;
import com.example.first.presentation.mainActivity.filmStrip.ItemAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.Nullable;

public class MainFragment extends Fragment implements AdapterListener {

    FragmentMainBinding binding;
    ItemAdapter adapter = new ItemAdapter(this, false);

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
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        senderViewModel = new ViewModelProvider(requireActivity(), sendViewModelFactory).get(SendViewModel.class);
        mainViewModel = new ViewModelProvider(requireActivity(), mainViewModelFactory).get(MainViewModel.class);

        mainViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
            adapter.setItems(items);
        });

        RecyclerView recyclerView = view.findViewById(binding.PopularRecyclerView.getId());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mainViewModel.getIsLoading().observe(getViewLifecycleOwner(), aBoolean -> {
            Log.d(Tag, String.valueOf(aBoolean));
            if (aBoolean) {
                binding.PopularRecyclerView.setVisibility(View.GONE);
                binding.loadingGIF.setVisibility(View.VISIBLE);

                Glide.with(requireContext())
                        .asGif()  // Загрузка как GIF
                        .load(R.drawable.koala)  // Используйте URL или ресурс
                        .placeholder(R.drawable.moai)  // Плейсхолдер пока GIF загружается
                        .error(R.drawable.ic_launcher_foreground)  // Показать в случае ошибки
                        .listener(new RequestListener<GifDrawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                                Log.e("Glide", "Failed to load GIF", e);
                                return false;  // Вернуть false, чтобы Glide обработал ошибку
                            }

                            @Override
                            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                                Log.i("Glide", "GIF loaded successfully");
                                return false;  // Вернуть false, чтобы Glide продолжил обработку ресурса
                            }
                        })
                        .into(binding.loadingGIF);

            } else {
                binding.PopularRecyclerView.setVisibility(View.VISIBLE);
                binding.loadingGIF.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onClick(ShortFilmModel filmModel) {
        Intent intent = new Intent(getActivity(), DescriptionFilmActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean longOnClick(ShortFilmModel filmModel) {
        if (!senderViewModel.selectItem(filmModel))
            Toast.makeText(getContext(), "Данный элемент уже находится в избранное", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void deleteFilm(ShortFilmModel filmModel) {

    }

    public void searchFilmByName(String name) {
        mainViewModel.searchFilmByName(name);
    }

    public void searchFilmCollection(String name) {
        mainViewModel.searchFilmByCollection(CollectionType.valueOf(name));
    }
}
