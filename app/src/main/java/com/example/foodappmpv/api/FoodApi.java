package com.example.foodappmpv.api;

import com.example.foodappmpv.model.Categories;
import com.example.foodappmpv.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {


    @GET("search.php?f=s")
    Call<Meals> getMeal();

    @GET("categories.php")
    Call<Categories> getCategories();


    @GET("filter.php")
    Call<Meals> getMealByCategory(@Query("c") String category);

}
