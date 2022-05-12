package com.example.urban_food.Activites.Wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityWalletBinding;

public class Wallet extends AppCompatActivity {

    ActivityWalletBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityWalletBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
    }
}