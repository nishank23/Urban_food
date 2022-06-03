package com.example.urban_food.Activities.WelcomeScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.urban_food.Activities.Login.LoginActivity;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.PrefUtils;
import com.example.urban_food.R;
import com.example.urban_food.databinding.WelcomeScreenBinding;

public class WelcomeScreenActivity extends AppCompatActivity {
WelcomeScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding=WelcomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));

        binding.buttonLoginWelcomeScreen.setOnClickListener(view -> {
            Intent intent=new Intent(WelcomeScreenActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}