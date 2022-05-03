package com.example.urban_food.fragment.favorite;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Modal.FavoriteModal.GetFavoriteResponse;
import com.example.urban_food.model.AddFavoriteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritePresenter {
    FavoriteView view;
    public FavoritePresenter(FavoriteView view){
        this.view=view;


    }

    public void addFavorite(String shopid){
        ApiClient.getRetrofit().addFavorite(shopid).enqueue(new Callback<AddFavoriteResponse>() {
            @Override
                public void onResponse(Call<AddFavoriteResponse> call, Response<AddFavoriteResponse> response) {
                if(response.isSuccessful() && response.body()!=null){
                    view.onSuccessFavorite(response.message());
                }else{
                }
            }

            @Override
            public void onFailure(Call<AddFavoriteResponse> call, Throwable t) {
            }
        });
    }

    public void getFavorite(){

    }


}
