package com.example.urban_food.Activites.ClickPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.urban_food.databinding.ActivityClickCuisineBinding;

public class ClickCuisineActivity extends AppCompatActivity {
    ActivityClickCuisineBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityClickCuisineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}