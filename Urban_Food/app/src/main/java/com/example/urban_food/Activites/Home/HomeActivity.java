package com.example.urban_food.Activites.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.urban_food.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding =ActivityHomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
    }
}