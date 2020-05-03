package com.example.foodappmpv.view.category;

import androidx.annotation.NonNull;

import com.example.foodappmpv.api.FoodClient;
import com.example.foodappmpv.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {

    private CategoryView categoryView;

    public CategoryPresenter(CategoryView categoryView) {
        this.categoryView = categoryView;
    }

    void getMealByCategory(String category){
        categoryView.showLoading();

        FoodClient.getInstance().getMealByCategory(category)
                .enqueue(new Callback<Meals>() {
                    @Override
                    public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                        categoryView.hideLoading();
                        if (response.isSuccessful() && response.body() != null){
                            categoryView.setMeals(response.body().getMeals());
                        }else {
                            categoryView.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                        categoryView.hideLoading();
                        categoryView.onErrorLoading(t.getLocalizedMessage());
                    }
                });
    }
}
