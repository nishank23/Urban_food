package com.example.urban_food.Activites.Login;

import com.example.urban_food.Modal.ProfileModal.AddressesItem;
import com.example.urban_food.Modal.ProfileModal.CartItem;

import java.util.List;

public interface LoginActivityView {

    void onSuccessLogin(String token);

    void onSuccessProfile(List<CartItem> cardItemlist, List<AddressesItem> addressesItemList);

    void onError(String msg);

    void ShowProgress();

    void dismissProgress();

}
