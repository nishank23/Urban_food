package com.example.urban_food.Activites.otp;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Modal.OtpModal.OtpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpPresenter {


    OtpView view;

    public OtpPresenter(OtpView view) {
        this.view = view;
    }

    public void callApiOtp(String phone){
        view.ShowProgress();
        ApiClient.getRetrofit().getingOtp(phone).enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    view.dismissProgress();
                    view.onSuccessOtp(response.body().getOtp());
                }else{
                    view.dismissProgress();
                    view.onError();
                }
            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {

            }
        });
    }

}
