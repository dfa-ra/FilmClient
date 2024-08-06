package com.example.first.presentation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.first.presentation.Fragments.FavoritesFragment;
import com.example.first.presentation.Fragments.MainFragment;
import com.example.first.R;
import com.example.first.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;
    List<Fragment> fragmentList = new ArrayList<Fragment>(){{
        add(MainFragment.getInstance());
        add(FavoritesFragment.getInstance());
    }};

    List<String> fragmentListNames = new ArrayList<String>(){{
        add("Популярное");
        add("Избранное");
    }};

    public final static String Tag = "MoyaProgamma";

    @SuppressLint({"CheckResult", "SetTextI18n", "MissingInflatedId", "CommitTransaction"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.i(Tag, "onCreate");
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        TabLayout tabLayout = binding.tabLayout;
        ViewPager2 viewPager = binding.viewPagerFragment;

        // Настройте адаптер для ViewPager
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, fragmentList);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(fragmentListNames.get(position));
            }
        }).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.fragmentTextView.setText(fragmentListNames.get(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Do nothing
            }
        });

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Здесь вы можете обработать текст поиска, когда пользователь нажимает кнопку поиска
                ((MainFragment) fragmentList.get(0)).searchFilmByName(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Здесь вы можете обработать текст поиска, когда пользователь вводит текст
                return false;
            }
        });

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(R.layout.custom_tab);
                TextView tabTextView = (TextView) tab.getCustomView();
                if (tabTextView != null) {
                    tabTextView.setText(fragmentListNames.get(i));
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}