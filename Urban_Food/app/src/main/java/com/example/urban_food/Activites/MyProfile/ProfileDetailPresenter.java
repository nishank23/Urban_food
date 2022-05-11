package com.example.urban_food.Activites.MyProfile;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.model.User;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileDetailPresenter {
    ProfileDetailView view;
    public ProfileDetailPresenter(ProfileDetailView view){
        this.view=view;
    }

    void updateProfile(RequestBody requestBody){
        ApiClient.getRetrofit().updateProfile(requestBody).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    view.onSuccessChange(response.message());
                }else{
                    Common.showSomethingWentWrong();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Common.showSomethingWentWrong();
            }
        });
    }
}
