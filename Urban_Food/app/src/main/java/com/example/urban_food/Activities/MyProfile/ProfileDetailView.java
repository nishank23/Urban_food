package com.example.urban_food.Activities.MyProfile;

import com.example.urban_food.model.User;

public interface ProfileDetailView {
    void onSuccessChange(User user);
    void onError();
    void showProgress();
    void dismissProgress();
    void  onSuccessProfile(User user);
}
