package com.example.urban_food.fragment.favorite;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Modal.FavoriteModal.GetFavoriteResponse;
import com.example.urban_food.model.AddFavoriteResponse;
import com.example.urban_food.model.FavoriteList;

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
                    Common.showToast("Error");
                }
            }

            @Override
            public void onFailure(Call<AddFavoriteResponse> call, Throwable t) {
                Common.showToast("Error");

            }
        });
    }

    public void getFavorite(){
        ApiClient.getRetrofit().getFavorite().enqueue(new Callback<FavoriteList>() {
            @Override
            public void onResponse(Call<FavoriteList> call, Response<FavoriteList> response) {
                if(response.isSuccessful() && response.body()!=null){
                    view.getFavorite(response.body());
                }else{
                    Common.showToast("Error");
                }
            }

            @Override
            public void onFailure(Call<FavoriteList> call, Throwable t) {
                Common.showToast("Error");
            }
        });
    }


}
