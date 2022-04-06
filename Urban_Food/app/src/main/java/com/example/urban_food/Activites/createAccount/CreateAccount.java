package com.example.urban_food.Activites.createAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.urban_food.databinding.ActivityCreateAccountBinding;

public class CreateAccount extends AppCompatActivity {
    ActivityCreateAccountBinding binding;
    String phoneString="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityCreateAccountBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        phoneString=getIntent().getStringExtra("phone");
        binding.etPhoneCreateAccount.setText("+91 "+phoneString);
        binding.etPhoneCreateAccount.setEnabled(false);
    }
}