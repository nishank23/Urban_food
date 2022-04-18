package com.example.urban_food.Activites.ShopsDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.urban_food.databinding.ActivityShopsDetailsBinding;

public class ShopsDetailsActivity extends AppCompatActivity {

    ActivityShopsDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityShopsDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}