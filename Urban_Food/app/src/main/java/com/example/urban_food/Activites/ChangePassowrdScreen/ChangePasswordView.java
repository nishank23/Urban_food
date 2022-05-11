package com.example.urban_food.Activites.ChangePassowrdScreen;

import com.example.urban_food.model.ChangePassword;

public interface ChangePasswordView {
    void onSuccessChange(String msg);

    void onError();

    void ShowProgress();

    void dismissProgress();
}
