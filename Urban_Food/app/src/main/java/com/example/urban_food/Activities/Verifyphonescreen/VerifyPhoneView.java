package com.example.urban_food.Activities.Verifyphonescreen;

import com.example.urban_food.model.ForgotPassword;

public interface VerifyPhoneView {

    void onSuccessOtp(int data);

    void onSucuessForget(ForgotPassword data);
    void onError();

    void ShowProgress();

    void dismissProgress();
}
