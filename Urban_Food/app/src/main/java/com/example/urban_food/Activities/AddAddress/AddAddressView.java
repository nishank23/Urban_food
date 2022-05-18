package com.example.urban_food.Activities.AddAddress;

import com.example.urban_food.model.Address;

import java.util.List;

public interface AddAddressView {
    void onSuccessAddress(Address response);
    void onError(String msg);
    void ShowProgress();
    void dismissProgress();
}
