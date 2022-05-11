package com.example.urban_food.Activites.Verifyphonescreen;

import com.example.urban_food.model.ForgotPassword;

public interface VerifyPhoneView {

    void onSuccessOtp(int data);

    void onSucuessForget(ForgotPassword data);
    void onError();

    void ShowProgress();

    void dismissProgress();
}
