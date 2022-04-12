package com.example.urban_food.fragment.explore;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Modal.ShopModal.ShopResponse;
import com.google.android.gms.common.api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopsPresenter {

    ShopsView view;

    public ShopsPresenter(ShopsView view) {
        this.view = view;
    }

    public void shops(String id,String lati, String longi){
        ApiClient.getRetrofit().getShops(id,lati,longi).enqueue(new Callback<ShopResponse>() {
            @Override
            public void onResponse(Call<ShopResponse> call, Response<ShopResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    view.dismissProgressShops();
                    view.onSuccessShops();
                }else{
                    view.onErrorShops();
                }
            }

            @Override
            public void onFailure(Call<ShopResponse> call, Throwable t) {

            }
        });
    }
}
