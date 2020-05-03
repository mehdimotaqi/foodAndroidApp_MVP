package com.example.foodappmpv.view.category;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodappmpv.R;
import com.example.foodappmpv.adapter.RecyclerViewMealByCategory;
import com.example.foodappmpv.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CategoryFragment extends Fragment implements CategoryView{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imageCategory)
    ImageView imageCategory;
    @BindView(R.id.imageCategoryBg)
    ImageView imageCategoryBg;
    @BindView(R.id.textCategory)
    TextView textCategory;

    AlertDialog.Builder descDialog;




    public CategoryFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments()!= null){
            textCategory.setText(getArguments().getString("EXTRE_DATA_DESC"));
            Picasso.get().load(getArguments().getString("EXTRE_DATA_IMAGE")).into(imageCategory);
            Picasso.get().load(getArguments().getString("EXTRE_DATA_IMAGE")).into(imageCategoryBg);
            descDialog = new AlertDialog.Builder(getContext())
                    .setTitle(getArguments().getString("EXTRE_DATA_NAME"))
                    .setMessage(getArguments().getString("EXTRE_DATA_DESC"));
            CategoryPresenter categoryPresenter = new CategoryPresenter(this);
            categoryPresenter.getMealByCategory(getArguments().getString("EXTRE_DATA_NAME"));
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<Meals.Meal> meals) {
        RecyclerViewMealByCategory adapter = new RecyclerViewMealByCategory(getContext(), meals);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener((view, position) -> {
            Toast.makeText(getContext(), "meal "+meals.get(position).getStrMeal(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onErrorLoading(String message) {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick
    public  void onClick(){
        descDialog.setOnDismissListener(dialog -> dialog.dismiss());
        descDialog.show();
    }
}
