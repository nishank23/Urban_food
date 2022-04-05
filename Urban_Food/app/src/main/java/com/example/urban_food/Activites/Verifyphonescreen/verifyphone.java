package com.example.urban_food.Activites.Verifyphonescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.urban_food.Activites.otp.OtpActivity;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityVerifyphoneBinding;

public class verifyphone extends AppCompatActivity {
    ActivityVerifyphoneBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityVerifyphoneBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.btnNext.setOnClickListener(view -> {
            if(binding.etPhoneNo.getText().toString().length() == 10){
                Intent intent=new Intent(this, OtpActivity.class);
                startActivity(intent);
            }else{
                Common.showToast(this,"Phone no is not valid");
            }

        });
    }
}