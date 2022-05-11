package com.example.urban_food.Activites.ChangePassowrdScreen;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.model.ChangePassword;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordPresenter {
    ChangePasswordView view;

    public ChangePasswordPresenter(ChangePasswordView view){
        this.view=view;
    }

    public void changePassword(HashMap<String,String> map){
        ApiClient.getRetrofit().changePassword(map).enqueue(new Callback<ChangePassword>() {
            @Override
            public void onResponse(Call<ChangePassword> call, Response<ChangePassword> response) {
                if(response.isSuccessful() && response.body()!=null){
                    view.onSuccessChange(response.body().getMessage());
                }else{
                    view.onError();
                }
            }

            @Override
            public void onFailure(Call<ChangePassword> call, Throwable t) {
                    Common.showToast("Something went wrong"+t.getLocalizedMessage());
            }
        });
    }


}
