package com.example.urban_food.Activites.ShopsDetail.cart;

import com.example.urban_food.model.AddCart;
import com.example.urban_food.model.Cart;

import java.util.List;

import retrofit2.Response;

public interface CartView {

    void onSuccessCartView(List<Cart> cartResponse);
    void onErrorCartView();
    void onSuccessGetCartView(List<Cart> getCartResponse);

    void showProgressShops();

    void dismissProgressShops();

}
