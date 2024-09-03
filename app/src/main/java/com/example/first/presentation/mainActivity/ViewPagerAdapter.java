package com.example.first.presentation.mainActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.first.presentation.mainActivity.Fragments.MyMainFragment;
import com.example.first.presentation.mainActivity.Fragments.mainFragment.MainFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private List<MyMainFragment> fragmentList;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<MyMainFragment> fragmentList) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}