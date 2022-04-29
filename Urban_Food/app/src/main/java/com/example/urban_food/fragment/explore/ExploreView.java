package com.example.urban_food.fragment.explore;

import com.example.urban_food.model.Cuisine;
import com.example.urban_food.model.RestaurantsData;
import com.example.urban_food.model.Shop;

import java.util.List;

public interface ExploreView {
    void onSuccessCuisine(List<Cuisine> cuisineResponseItems);
    void onSuccessShops(List<Shop> shopsItemList);

    void onErrorShops();

    void showProgressShops();

    void dismissProgressShops();

}
