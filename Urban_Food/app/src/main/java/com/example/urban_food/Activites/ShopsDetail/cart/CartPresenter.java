package com.example.urban_food.Activites.ShopsDetail.cart;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.model.AddCart;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartPresenter {

    CartView view;

    public CartPresenter(CartView view) {
        this.view = view;
    }

    public void callCart(HashMap<String,String> map){

        ApiClient.getRetrofit().postCartDetails(map).enqueue(new Callback<AddCart>() {
            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                if(response.isSuccessful() && response.body() != null){
                    view.onSuccessCartView(response.body().getProductList());
                }
            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {
                view.onErrorCartView();
            }
        });
    }
    public void getCallCart(){

        ApiClient.getRetrofit().getCartDetail().enqueue(new Callback<AddCart>() {
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
        });
    }
}
