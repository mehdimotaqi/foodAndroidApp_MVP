package com.example.foodappmpv.view.home;

import androidx.annotation.NonNull;

import com.example.foodappmpv.api.FoodClient;
import com.example.foodappmpv.model.Categories;
import com.example.foodappmpv.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {

    private HomeView homeView;

    protected HomePresenter(HomeView homeView) {
        this.homeView = homeView;
    }


    void getMeals(){
        homeView.showLoading();

        FoodClient.getInstance().getMeal().enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                homeView.hideLoading();
                if (response.isSuccessful() && response.body() != null){
                    homeView.setMeal(response.body().getMeals());
                }else {
                    homeView.onError(response.message());
                }

            }

            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                homeView.hideLoading();
                homeView.onError(t.getLocalizedMessage());
            }
        });
    }


    void getCategories(){
        homeView.showLoading();

        FoodClient.getInstance().getCategories().enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(@NonNull Call<Categories> call,@NonNull Response<Categories> response) {
                homeView.hideLoading();
                if (response.isSuccessful() && response.body() != null){
                    homeView.setCategory(response.body().getCategories());
                }else {
                    homeView.onError(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Categories> call, Throwable t) {
                homeView.hideLoading();
                homeView.onError(t.getLocalizedMessage());
            }
        });
    }
}
