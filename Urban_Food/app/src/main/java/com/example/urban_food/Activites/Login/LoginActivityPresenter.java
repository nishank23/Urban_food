package com.example.urban_food.Activites.Login;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Modal.LoginModal.LoginResponse;
import com.example.urban_food.Modal.ProfileModal.ProfileResponse;

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
        ApiClient.getRetrofit().Login(map).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                view.dismissProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onSuccessLogin(response.body().getAccessToken());
                } else {
                    view.onError("Either phone no or password is wrong");

                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                view.onError("Either phone no or password is wrong");
            }
        });
    }

    public void getProfile(HashMap<String, String> map) {
        view.ShowProgress();

        ApiClient.getRetrofit().getProfile(map).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                view.dismissProgress();
                if (response.isSuccessful() && response.body() != null) {
                    view.onSuccessProfile(response.body().getCart(), response.body().getAddresses());
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
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                view.dismissProgress();
                view.onError("Something went wrong");
            }
        });
    }
}
