package com.example.urban_food.fragment.explore;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.model.Cuisine;
import com.example.urban_food.model.RestaurantsData;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExplorePresenter {

    ExploreView view;

    public ExplorePresenter(ExploreView view) {
        this.view = view;
    }

    public void shops(HashMap<String,String> map) {
        view.showProgressShops();
        ApiClient.getRetrofit().getShops(map).enqueue(new Callback<RestaurantsData>() {
            @Override
            public void onResponse(Call<RestaurantsData> call, Response<RestaurantsData> response) {
                view.dismissProgressShops();
                if (response.isSuccessful() && response.body() != null) {
                    view.onSuccessShops(response.body().getShops());
                } else {
                    view.onErrorShops();
                }
            }

            @Override
            public void onFailure(Call<RestaurantsData> call, Throwable t) {
                view.onErrorShops();

            }
        });
    }

    public void shopsPopular(HashMap<String,String> map) {
        view.showProgressShops();
        ApiClient.getRetrofit().getShops(map).enqueue(new Callback<RestaurantsData>() {
            @Override
            public void onResponse(Call<RestaurantsData> call, Response<RestaurantsData> response) {
                view.dismissProgressShops();
                if (response.isSuccessful() && response.body() != null) {
                    view.onSuccessShopsPopular(response.body().getShops());
                } else {
                    view.onErrorShops();
                }
            }

            @Override
            public void onFailure(Call<RestaurantsData> call, Throwable t) {
                view.onErrorShops();

            }
        });
    }

    public void cuisine() {
        ApiClient.getRetrofit().getCuisineCategories().enqueue(new Callback<List<Cuisine>>() {
            @Override
            public void onResponse(Call<List<Cuisine>> call, Response<List<Cuisine>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.dismissProgressShops();
                    view.onSuccessCuisine(response.body());
                } else{

                }
            }
                @Override
                public void onFailure (Call <List<Cuisine>> call, Throwable t){

                }
            });
        }
    }
