package com.example.urban_food.Activites.Login;

import androidx.annotation.NonNull;

import com.example.urban_food.Activites.createAccount.CreateAccountView;
import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Modal.LoginModal.LoginResponse;
import com.example.urban_food.Modal.RegisterModal.RegistrationResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityPresenter {

    LoginActivityView view;

    public LoginActivityPresenter(LoginActivityView view) {
        this.view = view;
    }

    public void login(HashMap<String, String> map){
        view.ShowProgress();
        ApiClient.getRetrofit().Login(map).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                view.dismissProgress();
                if(response.isSuccessful() && response.body() != null){

                    view.onSuccessLogin(response.body().getAccessToken());
                }else{
                    view.onError("Either phone no or password is wrong");

                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                view.onError("Either phone no or password is wrong");
            }
        });
    }
}
