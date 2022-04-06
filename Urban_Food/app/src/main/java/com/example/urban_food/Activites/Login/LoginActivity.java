package com.example.urban_food.Activites.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.urban_food.Activites.Verifyphonescreen.verifyphone;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private final Activity activity = LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Common.isConnected(activity)){
            binding.tvSignUp.setOnClickListener(view -> {
                Intent intent=new Intent(this, verifyphone.class);
                intent.putExtra("checker","true");
                startActivity(intent);
            });
            binding.tvForgrtPasswordLogin.setOnClickListener(view -> {
                Intent intent=new Intent(this, verifyphone.class);
                intent.putExtra("checker","false");
                startActivity(intent);
            });
        }else {
            Common.showNoInternet(activity);
        }
    }
}