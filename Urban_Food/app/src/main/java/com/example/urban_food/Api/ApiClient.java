package com.example.urban_food.Api;

import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.MyApplication;
import com.example.urban_food.Helper.PrefUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    public static ApiInterface getRetrofit() {
        OkHttpClient client = getClient();

        Gson gson = new GsonBuilder().create();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://cash.grabfoodies.com/public/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(ApiInterface.class);
    }
    private static OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .addInterceptor(chain -> chain
                        .proceed(
                                chain.request()
                                        .newBuilder()
                                        .addHeader("X-Requested-With", "XMLHttpRequest")
                                        .addHeader("Authorization", PrefUtils.getStringPref(Common.userToken, MyApplication.instance))
                                        .addHeader("Connection","close")
                                        .build()
                        ))
                .build();
        client.connectionPool().evictAll();
        return client;
    }
}