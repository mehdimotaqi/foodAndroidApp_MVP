package com.example.foodappmpv.api;

import com.example.foodappmpv.model.Categories;
import com.example.foodappmpv.model.Meals;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodClient {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    private static FoodClient instance;
    private FoodApi foodApi;

    private FoodClient() {
       Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
            .client(provideOkHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

       foodApi = retrofit.create(FoodApi.class);
    }


    private static Interceptor provideLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }


    private static OkHttpClient provideOkHttp(){
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(provideLoggingInterceptor())
                .build();
    }

    public static FoodClient getInstance() {
        if (instance == null) {
            instance = new FoodClient();
        }
        return instance;
    }


    public Call<Meals> getMeal(){
        return foodApi.getMeal();
    }


    public Call<Categories> getCategories(){
        return foodApi.getCategories();
    }


    public Call<Meals> getMealByCategory(String category){
       return foodApi.getMealByCategory(category);
    }
}
