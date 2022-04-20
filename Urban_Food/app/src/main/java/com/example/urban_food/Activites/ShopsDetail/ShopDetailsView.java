package com.example.urban_food.Activites.ShopsDetail;

import com.example.urban_food.Modal.ShopsDetailsModal.ShopDetailsCategoriesItem;

import java.util.List;

public interface ShopDetailsView {

    void onSuccessShopDetails(List<ShopDetailsCategoriesItem> shopDetailList);
    void onErrorShopDetails();
    void showProgressShopDetails();
    void dismissShopDetails();

}
