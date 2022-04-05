package com.example.urban_food.Activites.otp;

public interface OtpView {

    void onSuccessOtp(int data);

    void onError();

    void ShowProgress();

    void dismissProgress();

}
