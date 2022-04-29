package com.example.urban_food.Activites.ShopsDetail;


import com.example.urban_food.model.Category;
import com.example.urban_food.model.ShopDetail;

import java.util.List;

public interface ShopDetailsView {

    void onSuccessShopDetails(List<Category> shopDetailList);
    void onErrorShopDetails();
    void showProgressShopDetails();
    void dismissShopDetails();

}
