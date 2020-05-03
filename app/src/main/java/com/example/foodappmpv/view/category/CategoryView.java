package com.example.foodappmpv.view.category;

import com.example.foodappmpv.model.Meals;

import java.util.List;

public interface CategoryView {

    void showLoading();
    void hideLoading();
    void setMeals(List<Meals.Meal> meals);
    void onErrorLoading(String message);
}
