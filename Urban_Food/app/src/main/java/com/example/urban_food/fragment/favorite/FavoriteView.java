package com.example.urban_food.fragment.favorite;

import com.example.urban_food.model.FavoriteList;

public interface FavoriteView {

    void onSuccessFavorite(String msg);
    void getFavorite(FavoriteList response);
    void deleteFavorite(String msg);

}
