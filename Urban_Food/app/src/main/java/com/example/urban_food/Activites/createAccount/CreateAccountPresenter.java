package com.example.urban_food.Activites.createAccount;

import com.example.urban_food.Activites.Verifyphonescreen.VerifyPhoneView;
import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Modal.RegisterModal.RegistrationResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountPresenter {

    CreateAccountView view;

    public CreateAccountPresenter(CreateAccountView view) {
        this.view = view;
    }

    public void registration(HashMap<String, String> map){
        ApiClient.getRetrofit().registration(map).enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                view.ShowProgress();
                if(response.isSuccessful() && response.body() != null){
                    view.dismissProgress();
                    view.onSuccessRegistration();
                }else{
                    view.onError();
                }

            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {

            }
        });
    }
}
