package com.example.urban_food.Activites.SplashScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

import com.example.urban_food.Activites.Home.HomeActivity;
import com.example.urban_food.Activites.Login.LoginActivity;
import com.example.urban_food.Activites.Login.LoginActivityPresenter;
import com.example.urban_food.Activites.Login.LoginActivityView;
import com.example.urban_food.Activites.WelcomeScreen.WelcomeScreenActivity;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.PrefUtils;
import com.example.urban_food.Modal.ProfileModal.AddressesItem;
import com.example.urban_food.Modal.ProfileModal.CartItem;
import com.example.urban_food.databinding.ActivitySpashScreenBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.List;

public class SpashScreenActivity extends AppCompatActivity implements LoginActivityView {
    ActivitySpashScreenBinding binding;
    LoginActivityPresenter loginActivityPresenter = new LoginActivityPresenter(this);

    private String device_id;
    private String fcm_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDeviceIdAndToken();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkactivity();
            }
        }, 3000);

    }

    public void checkactivity() {
        if (PrefUtils.getBooleanPref(Common.isLoggedIn, this)) {
            //Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();
            if (PrefUtils.getStringPref(Common.userToken, this).isEmpty()) {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            } else {
                HashMap<String, String> map = new HashMap<>();
                map.put("device_type", "android");
                map.put("device_id", device_id);
                map.put("device_token", fcm_token);
                loginActivityPresenter.getProfile(map);
            }

        } else {
            startActivity(new Intent(this, WelcomeScreenActivity.class));
            finish();
        }

    }

    private void getDeviceIdAndToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("LoginActivity", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token

                        // Log and toast
                        fcm_token = task.getResult();

                    }
                });

        try {
            device_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            device_id = "";
        }

    }

    @Override
    public void onSuccessLogin(String token) {

    }

    @Override
    public void onSuccessProfile(List<CartItem> cardItemlist, List<AddressesItem> addressesItemList) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onError(String msg) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void ShowProgress() {

    }

    @Override
    public void dismissProgress() {

    }
}