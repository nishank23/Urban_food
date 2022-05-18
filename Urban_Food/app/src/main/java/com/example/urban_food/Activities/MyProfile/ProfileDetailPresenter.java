package com.example.urban_food.Activities.MyProfile;

import android.util.Log;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.model.User;

import java.io.IOException;
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
                    view.onSuccessChange(response.body());
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

    public void getProfile(HashMap<String, String> map) {

        ApiClient.getRetrofit().getProfile(map).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                view.dismissProgress();
                if (response.isSuccessful() && response.body() != null) {
                    view.onSuccessProfile(response.body());
                } else {
                    Common.showSomethingWentWrong();

                    try {
                        String error = response.errorBody().string();
                        Log.d("error_profile", error);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Common.showSomethingWentWrong();

            }
        });
    }

}
