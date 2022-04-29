package com.example.urban_food.Activites.Verifyphonescreen;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.model.Otp;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyPhonePresenter {

    VerifyPhoneView view;

    public VerifyPhonePresenter(VerifyPhoneView view) {
        this.view = view;
    }

    public void callApiOtp(String phone){
        view.ShowProgress();
        ApiClient.getRetrofit().getingOtp(phone).enqueue(new Callback<Otp>() {
            @Override
            public void onResponse(Call<Otp> call, Response<Otp> response) {
                if(response.isSuccessful() && response.body() != null){
                    view.dismissProgress();
                    view.onSuccessOtp(response.body().getOtp());
                }else{
                    view.dismissProgress();
                    view.onError();
                }
            }

            @Override
            public void onFailure(Call<Otp> call, Throwable t) {

            }
        });
    }

}
