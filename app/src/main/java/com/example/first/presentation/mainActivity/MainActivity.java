package com.example.first.presentation.mainActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.first.R;
import com.example.first.databinding.ActivityMainBinding;
import com.example.first.presentation.mainActivity.Fragments.favoritesFragment.FavoritesFragment;
import com.example.first.presentation.mainActivity.Fragments.mainFragment.MainFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;
    protected String collectionName = "Популярное";
    protected String favoriteFragmentName = "Избранное";

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

        Log.i("aa99", "onCreate");

        TabLayout tabLayout = binding.tabLayout;
        tabLayout.setVisibility(View.GONE);
        ViewPager2 viewPager = binding.viewPagerFragment;

        // Настройте адаптер для ViewPager
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, fragmentList);
        viewPager.setAdapter(adapter);

        binding.fragmentNameTextView.setText(collectionName);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                tab.setText(fragmentListNames.get(position));
            }
        }).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    binding.fragmentNameTextView.setText(collectionName);
                }else {
                    binding.fragmentNameTextView.setText(favoriteFragmentName);
                }
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

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                ((MainFragment) fragmentList.get(0)).searchFilmCollection(String.valueOf(item.getTitleCondensed()));
                collectionName = (String) item.getTitle();
                binding.fragmentNameTextView.setText(collectionName);
                binding.drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        binding.navigationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawer.openDrawer(GravityCompat.START);
            }
        });

        binding.searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("aa99", "setOnSearchClickListener");
                binding.fragmentNameTextView.setVisibility(View.GONE);
                binding.navigationImage.setVisibility(View.GONE);
            }
        });

        binding.searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                binding.fragmentNameTextView.setVisibility(View.VISIBLE);
                binding.navigationImage.setVisibility(View.VISIBLE);
                return false;
            }
        });

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                collectionName = "Поиск по \"" + query + "\"";
                binding.fragmentNameTextView.setText("Поиск по \"" + query + "\"");
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