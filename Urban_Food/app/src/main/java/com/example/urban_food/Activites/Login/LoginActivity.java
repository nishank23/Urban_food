package com.example.urban_food.Activites.Login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.urban_food.Activites.Home.HomeActivity;
import com.example.urban_food.Activites.Verifyphonescreen.VerifyPhone;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.Helper.PrefUtils;
import com.example.urban_food.Modal.ProfileModal.AddressesItem;
import com.example.urban_food.Modal.ProfileModal.CartItem;
import com.example.urban_food.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements LoginActivityView {
    ActivityLoginBinding binding;
    private final Activity activity = LoginActivity.this;
    private ProgressDialog progressDialog;
    LoginActivityPresenter loginActivityPresenter = new LoginActivityPresenter(this);

    private String device_id;
    private String fcm_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getDeviceIdAndToken();

        if (Common.isConnected()) {
            binding.tvSignUp.setOnClickListener(view -> {
                Intent intent = new Intent(this, VerifyPhone.class);
                intent.putExtra("checker", "true");
                startActivity(intent);
            });
            binding.tvForgrtPasswordLogin.setOnClickListener(view -> {
                Intent intent = new Intent(this, VerifyPhone.class);
                intent.putExtra("checker", "false");
                startActivity(intent);
            });

            binding.buttonLogin.setOnClickListener(view -> {
                if (binding.etPhoneNo.getText().toString().isEmpty() && binding.etPhoneNo.getText().length() < 10) {
                    Common.showToast("Please Enter Phone Number");
                }
                if (binding.etPassword.getText().toString().length() < 6 && binding.etPassword.getText().toString().isEmpty()) {
                    Common.showToast("Please Enter Correct Password");
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("username", "+91" + binding.etPhoneNo.getText().toString());
                    map.put("password", binding.etPassword.getText().toString());
                    map.put("grant_type", "password");
                    map.put("client_id", "2");
                    map.put("client_secret", "OkjVHNOhn8rljc5cxyq0unICJ3qfotydQ5lvv88w");
                    loginActivityPresenter.login(map);


                }
            });
        } else {
            Common.showNoInternet();
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

        PrefUtils.putBooleanPref(Common.isLoggedIn, true, this);
        PrefUtils.putStringPref(Common.userToken, "Bearer " + token, this);

        HashMap<String, String> map = new HashMap<>();
        map.put("device_type", "android");
        map.put("device_id", device_id);
        map.put("device_token", fcm_token);

        loginActivityPresenter.getProfile(map);
    }

    @Override
    public void onSuccessProfile(List<CartItem> cardItemlist, List<AddressesItem> addressesItemList) {
        GlobalData.Cart = cardItemlist;
        GlobalData.Address = addressesItemList;
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String msg) {
        Common.showToast(msg);
    }

    @Override
    public void ShowProgress() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Logging In...");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}