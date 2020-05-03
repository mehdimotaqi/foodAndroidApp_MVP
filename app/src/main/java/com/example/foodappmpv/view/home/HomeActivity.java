package com.example.foodappmpv.view.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.foodappmpv.R;
import com.example.foodappmpv.adapter.RecyclerViewHomeAdapter;
import com.example.foodappmpv.adapter.ViewPagerHeaderAdapter;
import com.example.foodappmpv.model.Categories;
import com.example.foodappmpv.model.Meals;
import com.example.foodappmpv.view.category.CategoryActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView{

    public static final String EXTRA_CATEGORY ="category";
    public static final String EXTRA_POSITION = "position";

    @BindView(R.id.view_pager_header) ViewPager viewPagerMeal;
    @BindView(R.id.recycle_category) RecyclerView recyclerViewCategory;

    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        homePresenter = new HomePresenter(this);
        homePresenter.getMeals();
        homePresenter.getCategories();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.shimmer_meal).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmer_meal).setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meal, this);
        viewPagerMeal.setAdapter(headerAdapter);
        viewPagerMeal.setPadding(20, 0, 150, 0);
        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener(new ViewPagerHeaderAdapter.ClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(HomeActivity.this, meal.get(position).getStrMeal(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        RecyclerViewHomeAdapter recyclerViewHomeAdapter = new RecyclerViewHomeAdapter(category, this);
        recyclerViewCategory.setAdapter(recyclerViewHomeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setClipToPadding(false);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        recyclerViewHomeAdapter.notifyDataSetChanged();

        recyclerViewHomeAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });

    }

    @Override
    public void onError(String message) {
        Log.d("meal name: ", "Error: "+message);
    }
}
