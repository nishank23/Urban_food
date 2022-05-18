package com.example.urban_food.Activities.ShopsDetail;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.model.ShopDetail;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopDetailsPresenter {

    ShopDetailsView view;

    public ShopDetailsPresenter(ShopDetailsView view) {
        this.view = view;
    }

    public void getShopDetails(HashMap<String, String> map){
        ApiClient.getRetrofit().getShopDetails(map).enqueue(new Callback<ShopDetail>() {
            @Override
            public void onResponse(Call<ShopDetail> call, Response<ShopDetail> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.dismissShopDetails();
                    view.onSuccessShopDetails(response.body().getCategories());
                } else {
                    view.onErrorShopDetails();
                }
            }

            @Override
            public void onFailure(Call<ShopDetail> call, Throwable t) {
                view.onErrorShopDetails();
            }
        });
    }
}
