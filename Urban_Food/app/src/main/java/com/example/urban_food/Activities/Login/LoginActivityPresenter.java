package com.example.urban_food.Activities.Login;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.model.LoginModel;
import com.example.urban_food.model.User;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityPresenter {

    LoginActivityView view;

    public LoginActivityPresenter(LoginActivityView view) {
        this.view = view;
    }


    public void login(HashMap<String, String> map) {
        view.ShowProgress();
        ApiClient.getRetrofit().Login(map).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                view.dismissProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onSuccessLogin(response.body().getAccessToken());
                } else {
                    view.onError(response.errorBody().toString());

                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                view.onError(""+t.getMessage()+""+t.getLocalizedMessage());
            }
        });
    }

    public void getProfile(HashMap<String, String> map) {
        view.ShowProgress();

        ApiClient.getRetrofit().getProfile(map).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                view.dismissProgress();
                if (response.isSuccessful() && response.body() != null) {
                    view.onSuccessProfile(response.body().getCart(), response.body().getAddresses(), response.body());
                } else {
                    try {
                        String error = response.errorBody().string();
                        Log.d("error_profile", error);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    view.onError("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                view.dismissProgress();
                view.onError("Something went wrong");
            }
        });
    }
}
