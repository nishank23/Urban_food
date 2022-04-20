package com.example.urban_food.Activites.SearchScreen;

import com.example.urban_food.Modal.ProfileModal.AddressesItem;
import com.example.urban_food.Modal.ProfileModal.CartItem;
import com.example.urban_food.Modal.SearchModal.ProductsItem;
import com.example.urban_food.Modal.SearchModal.SearchResponse;

import java.util.List;

public interface SearchActivityView {

    void onSuccessSearch(SearchResponse response);

    void onError(String msg);

    void ShowProgress();

    void dismissProgress();

}
