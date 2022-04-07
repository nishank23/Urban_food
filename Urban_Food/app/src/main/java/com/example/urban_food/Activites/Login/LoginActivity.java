package com.example.urban_food.Activites.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.urban_food.Activites.Home.HomeActivity;
import com.example.urban_food.Activites.Verifyphonescreen.VerifyPhone;
import com.example.urban_food.Activites.createAccount.CreateAccountPresenter;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.PrefUtils;
import com.example.urban_food.databinding.ActivityLoginBinding;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements LoginActivityView {
    ActivityLoginBinding binding;
    private final Activity activity = LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Common.isConnected(activity)){
            binding.tvSignUp.setOnClickListener(view -> {
                Intent intent=new Intent(this, VerifyPhone.class);
                intent.putExtra("checker","true");
                startActivity(intent);
            });
            binding.tvForgrtPasswordLogin.setOnClickListener(view -> {
                Intent intent=new Intent(this, VerifyPhone.class);
                intent.putExtra("checker","false");
                startActivity(intent);
            });

            binding.buttonLogin.setOnClickListener(view -> {
                if(binding.etPhoneNo.getText().toString().isEmpty() && binding.etPhoneNo.getText().length()<10){
                    Common.showToast(this,"Please Enter Phone Number");
                }
                if(binding.etPassword.getText().toString().length()<6 && binding.etPassword.getText().toString().isEmpty()){
                    Common.showToast(this,"Please Enter Correct Password");
                }
                else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("username", "+91"+binding.etPhoneNo.getText().toString());
                    map.put("password", binding.etPassword.getText().toString());
                    map.put("grant_type","password");
                    map.put("client_id","2");
                    map.put("client_secret","OkjVHNOhn8rljc5cxyq0unICJ3qfotydQ5lvv88w");

                    LoginActivityPresenter loginActivityPresenter=new LoginActivityPresenter(this);
                    loginActivityPresenter.login(map);


                }
            });
        }else {
            Common.showNoInternet(activity);
        }
    }



    @Override
    public void onSuccessLogin(String token) {

        PrefUtils.putBooleanPref(Common.isLoggedIn,true,this);
        Intent intent=new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError(String msg) {
        Common.showToast(this,msg);
    }

    @Override
    public void ShowProgress() {

    }

    @Override
    public void dismissProgress() {

    }
}