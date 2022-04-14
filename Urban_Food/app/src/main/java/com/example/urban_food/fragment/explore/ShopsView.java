package com.example.urban_food.fragment.explore;

import com.example.urban_food.Modal.ShopModal.ShopsItem;

import java.util.List;

public interface ShopsView {

    void onSuccessShops(List<ShopsItem> shopsItemList);

    void onErrorShops();

    void showProgressShops();

    void dismissProgressShops();

}
