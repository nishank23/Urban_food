package com.example.urban_food.Activites.Login;

import com.example.urban_food.model.Address;
import com.example.urban_food.model.Cart;

import java.util.List;

public interface LoginActivityView {

    void onSuccessLogin(String token);

    void onSuccessProfile(List<Cart> cardItemlist, List<Address> addressesItemList);

    void onError(String msg);

    void ShowProgress();

    void dismissProgress();

}
