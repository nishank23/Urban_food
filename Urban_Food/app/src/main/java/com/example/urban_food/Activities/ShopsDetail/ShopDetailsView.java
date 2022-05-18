package com.example.urban_food.Activities.ShopsDetail;


import com.example.urban_food.model.Category;

import java.util.List;

public interface ShopDetailsView {

    void onSuccessShopDetails(List<Category> shopDetailList);
    void onErrorShopDetails();
    void showProgressShopDetails();
    void dismissShopDetails();

}
