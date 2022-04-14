package com.example.urban_food.fragment.explore;

import com.example.urban_food.Modal.CuisineModal.Cuisine;
import com.example.urban_food.Modal.ExploreModal.ShopsItem;

import java.util.List;

public interface ExploreView {
    void onSuccessCuisine(List<Cuisine> cuisineResponseItems);
    void onSuccessShops(List<ShopsItem> shopsItemList);

    void onErrorShops();

    void showProgressShops();

    void dismissProgressShops();

}
