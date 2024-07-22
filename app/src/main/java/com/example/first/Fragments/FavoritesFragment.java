package com.example.first.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.first.MainActivity;
import com.example.first.R;
import com.example.first.filmStrip.ItemAdapter;

public class FavoritesFragment extends Fragment {
    public final static String Tag = "FavoritesFragmentTAG";

    private static volatile FavoritesFragment fragment = null;
    private ItemAdapter adapter;

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
        adapter = new ItemAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(Tag, "onCreateView");
        Log.i(MainActivity.Tag, "FavoritesFragment");
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }
}
