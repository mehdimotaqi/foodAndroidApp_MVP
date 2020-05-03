package com.example.foodappmpv.view.category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodappmpv.R;
import com.example.foodappmpv.adapter.ViewPagerCategoryAdapter;
import com.example.foodappmpv.model.Categories;
import com.example.foodappmpv.view.home.HomeActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tablayout) TabLayout tabLayout;
    @BindView(R.id.viewPager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        initActionBar();
        initIntent();
    }

    private void initIntent() {
        Intent intent = getIntent();
        List<Categories.Category> categories = (List<Categories.Category>) intent.getSerializableExtra(HomeActivity.EXTRA_CATEGORY);
        int position = intent.getIntExtra(HomeActivity.EXTRA_POSITION, 0);
        ViewPagerCategoryAdapter viewPagerCategoryAdapter = new ViewPagerCategoryAdapter(getSupportFragmentManager(), categories);
        viewPager.setAdapter(viewPagerCategoryAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position, true);
        viewPagerCategoryAdapter.notifyDataSetChanged();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
