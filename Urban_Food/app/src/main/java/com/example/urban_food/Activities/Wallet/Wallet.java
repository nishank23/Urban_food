package com.example.urban_food.Activities.Wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.databinding.ActivityWalletBinding;

public class Wallet extends AppCompatActivity {

    ActivityWalletBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityWalletBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding.ivBackMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


}