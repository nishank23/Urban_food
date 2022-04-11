package com.example.urban_food.Api;

import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.MyApplication;
import com.example.urban_food.Helper.PrefUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    public static ApiInterface getRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> chain
                .proceed(
                        chain.request()
                        .newBuilder()
                        .addHeader("X-Requested-With", "XMLHttpRequest")
                        .addHeader("Authorization", PrefUtils.getStringPref(Common.userToken, MyApplication.instance))
                        .build()
                )).build();

        Gson gson = new GsonBuilder().create();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://cash.grabfoodies.com/public/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(ApiInterface.class);
    }
}