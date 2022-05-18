package com.example.urban_food.Activities.ShopsDetail.cart;

import com.example.urban_food.model.AddCart;
import com.example.urban_food.model.Cart;

import java.util.List;

public interface CartView {

    void onSuccessCartView(List<Cart> cartResponse);
    void onErrorCartView();
    void onSuccessGetCartView(AddCart getCartResponse);
    void onSuccessGetClearCartView(String message);

    void showProgressShops();

    void dismissProgressShops();

}
