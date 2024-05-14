package com.example.apiretrofit.service;

import com.example.apiretrofit.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("/products")
    Call<List<UserModel>> getUser();

}
