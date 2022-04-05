package com.example.urban_food.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    public static ApiInterface getRetrofit() {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://cash.grabfoodies.com/public/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(ApiInterface.class);
    }
}