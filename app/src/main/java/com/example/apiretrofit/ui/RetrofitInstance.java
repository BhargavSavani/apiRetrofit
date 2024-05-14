package com.example.apiretrofit.ui;

import static com.example.apiretrofit.ui.MainActivity.api;

import com.example.apiretrofit.service.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitInstance {
    public static RetrofitInstance instance;
    public ApiInterface apiInterface;
    RetrofitInstance() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }
    public static RetrofitInstance getInstance() {
        if (instance == null) {
            instance = new RetrofitInstance();
        }
        return instance;
    }
}
