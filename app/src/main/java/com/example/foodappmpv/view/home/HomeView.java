package com.example.foodappmpv.view.home;

import com.example.foodappmpv.model.Categories;
import com.example.foodappmpv.model.Meals;

import java.util.List;

public interface HomeView {

    void showLoading();
    void hideLoading();
    void setMeal(List<Meals.Meal> meal);
    void setCategory(List<Categories.Category> category);
    void onError(String message);
}
