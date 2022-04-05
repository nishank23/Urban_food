package com.example.urban_food.Activites.WelcomeScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.urban_food.Activites.Login.LoginActivity;
import com.example.urban_food.R;
import com.example.urban_food.databinding.WelcomeScreenBinding;

public class WelcomeScreenActivity extends AppCompatActivity {
WelcomeScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=WelcomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonLoginWelcomeScreen.setOnClickListener(view -> {
            Intent intent=new Intent(WelcomeScreenActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}