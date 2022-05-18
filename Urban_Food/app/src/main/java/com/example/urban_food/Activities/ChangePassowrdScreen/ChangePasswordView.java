package com.example.urban_food.Activities.ChangePassowrdScreen;

public interface ChangePasswordView {
    void onSuccessChange(String msg);

    void onError();

    void ShowProgress();

    void dismissProgress();
}
