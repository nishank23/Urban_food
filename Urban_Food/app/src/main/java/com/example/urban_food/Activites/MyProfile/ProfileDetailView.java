package com.example.urban_food.Activites.MyProfile;

public interface ProfileDetailView {
    void onSuccessChange(String msg);
    void onError();
    void showProgress();
    void dismissProgress();
}
