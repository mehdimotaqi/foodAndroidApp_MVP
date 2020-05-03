package com.example.foodappmpv.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.foodappmpv.model.Categories;
import com.example.foodappmpv.view.category.CategoryFragment;

import java.util.List;

public class ViewPagerCategoryAdapter extends FragmentPagerAdapter {

    private List<Categories.Category> categories;

    public ViewPagerCategoryAdapter(@NonNull FragmentManager fm, List<Categories.Category> categories) {
        super(fm);
        this.categories = categories;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("EXTRE_DATA_NAME", categories.get(position).getStrCategory());
        args.putString("EXTRE_DATA_DESC", categories.get(position).getStrCategoryDescription());
        args.putString("EXTRE_DATA_IMAGE", categories.get(position).getStrCategoryThumb());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return categories.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getStrCategory();
    }
}
