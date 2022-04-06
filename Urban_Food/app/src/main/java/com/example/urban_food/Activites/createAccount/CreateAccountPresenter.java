package com.example.urban_food.Activites.createAccount;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Modal.RegisterModal.RegistrationResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountPresenter {

    public void registration(HashMap<String, String> map){
        ApiClient.getRetrofit().registration(map).enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {

            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {

            }
        });
    }
}
