package com.example.urban_food.fragment.favorite;

import com.example.urban_food.Modal.FavoriteModal.AvailableItem;
import com.example.urban_food.Modal.FavoriteModal.GetFavoriteResponse;
import com.example.urban_food.model.Cuisine;
import com.example.urban_food.model.FavoriteList;
import com.example.urban_food.model.Shop;

import java.util.List;

public interface FavoriteView {

    void onSuccessFavorite(String msg);
    void getFavorite(FavoriteList response);

}
