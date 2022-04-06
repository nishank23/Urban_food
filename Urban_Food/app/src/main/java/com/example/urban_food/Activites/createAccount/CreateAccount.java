package com.example.urban_food.Activites.createAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.urban_food.Helper.Common;
import com.example.urban_food.databinding.ActivityCreateAccountBinding;

import java.util.HashMap;

public class CreateAccount extends AppCompatActivity {
    ActivityCreateAccountBinding binding;
    String phoneString="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityCreateAccountBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        phoneString=getIntent().getStringExtra("phone");
        binding.etPhoneCreateAccount.setText(phoneString);
        binding.etPhoneCreateAccount.setEnabled(false);

        clickEvent();
    }

    private void clickEvent() {
        binding.buttonSignUp.setOnClickListener(view -> {
            if(binding.etUserNameCrateAccount.getText().toString().isEmpty()){
                Common.showToast(this,"Username is empty");
            }else if(binding.etEmailCrateAccount.getText().toString().isEmpty()){
                Common.showToast(this,"Email is empty");
            }else if(binding.etBirthCreateAccount.getText().toString().isEmpty()){
                Common.showToast(this,"Birthdate is empty");
            }else if(binding.etPasswordCreateAccount.getText().toString().isEmpty()){
                Common.showToast(this,"password is empty");
            }else{
                HashMap<String, String> map = new HashMap<>();
                map.put("name", binding.etUserNameCrateAccount.getText().toString());
                map.put("email", binding.etEmailCrateAccount.getText().toString());
                map.put("phone", binding.etPhoneCreateAccount.getText().toString());
                map.put("password", binding.etPasswordCreateAccount.getText().toString());
                map.put("password_confirmation", binding.etPasswordCreateAccount.getText().toString());

                CreateAccountPresenter createAccountPresenter=new CreateAccountPresenter();
                createAccountPresenter.registration(map);

            }

        });
    }
}