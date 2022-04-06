package com.example.urban_food.Activites.Verifyphonescreen;

public interface VerifyphoneView {

    void onSuccessOtp(int data);

    void onError();

    void ShowProgress();

    void dismissProgress();
}
