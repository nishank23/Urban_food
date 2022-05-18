package com.example.urban_food.Activities.ManageAddress;

import com.example.urban_food.model.Address;

import java.util.List;

public interface AddressInterface {
    void ongetAddress(List<Address> address);
    void editClick(Address addressList);
    void deleteClick(String id);
    void onDelete(String msg);
    void ShowProgress();

    void dismissProgress();


}
