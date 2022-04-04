package com.example.urban_food.Activites.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.urban_food.databinding.ActivityOtpBinding;

public class OtpActivity extends AppCompatActivity {
    ActivityOtpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityOtpBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
    }
}