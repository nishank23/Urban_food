package com.example.urban_food.Activites.Login;

public interface LoginActivityView {

    void onSuccessLogin(String token);

    void onError(String msg);

    void ShowProgress();

    void dismissProgress();

}
