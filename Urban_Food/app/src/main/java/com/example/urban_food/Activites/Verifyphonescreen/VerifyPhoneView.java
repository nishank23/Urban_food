package com.example.urban_food.Activites.Verifyphonescreen;

public interface VerifyPhoneView {

    void onSuccessOtp(int data);

    void onError();

    void ShowProgress();

    void dismissProgress();
}
