package com.example.urban_food.Activities.ShopsDetail.cart;

import android.util.Log;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.model.AddCart;
import com.example.urban_food.model.ClearCart;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartPresenter {

    CartView view;

    public CartPresenter(CartView view) {
        this.view = view;
    }

    public void callCart(HashMap<String, String> map) {
        view.showProgressShops();

        ApiClient.getRetrofit().postCartDetails(map).enqueue(new Callback<AddCart>() {
            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {

                if (response.isSuccessful() && response.body() != null) {
                    view.dismissProgressShops();
                    view.onSuccessCartView(response.body());
                } else {
                    view.dismissProgressShops();
                    view.onErrorCartView();
                }
            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {
                view.onErrorCartView();
            }
        });
    }

    public void getCallCart() {

        ApiClient.getRetrofit().getCartDetail().enqueue(new Callback<AddCart>() {
            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.onSuccessGetCartView(response.body());
                    Log.d("test1",""+response.body().getTotalPrice().toString());
                } else {
                    view.dismissProgressShops();
                    view.onErrorCartView();
                }
            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {
                view.dismissProgressShops();
                view.onErrorCartView();
            }
        });
    /*getCartDetail().enqueue(new Callback<AddCart>() {
            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                view.showProgressShops();
                if(response.isSuccessful() && response.body() != null){
                    view.dismissProgressShops();
                    view.onSuccessGetCartView(response.body().getProductList());
                }
                else{
                    view.dismissProgressShops();
                    view.onErrorCartView();
                }
            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {
                view.dismissProgressShops();
                view.onErrorCartView();
            }
        });*/
    }

    public void getClearCart() {
        view.showProgressShops();
        ApiClient.getRetrofit().clearCartCall().enqueue(new Callback<ClearCart>() {
            @Override
            public void onResponse(Call<ClearCart> call, Response<ClearCart> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.dismissProgressShops();
                    view.onSuccessGetClearCartView(response.message());
                }
            }

            @Override
            public void onFailure(Call<ClearCart> call, Throwable t) {
                view.dismissProgressShops();
                view.onErrorCartView();
            }
        });
    }
}
