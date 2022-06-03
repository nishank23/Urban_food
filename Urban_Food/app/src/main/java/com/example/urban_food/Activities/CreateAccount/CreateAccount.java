package com.example.urban_food.Activities.CreateAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.urban_food.Activities.Login.LoginActivity;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.databinding.ActivityCreateAccountBinding;

import java.util.HashMap;

public class CreateAccount extends AppCompatActivity implements CreateAccountView{
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
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        clickEvent();
    }

    private void clickEvent() {
        binding.buttonSignUp.setOnClickListener(view -> {
            if(binding.etUserNameCrateAccount.getText().toString().isEmpty()){
                Common.showToast("Username is empty");
            }else if(binding.etEmailCrateAccount.getText().toString().isEmpty()){
                Common.showToast("Email is empty");
            }else if(binding.etPasswordCreateAccount.getText().toString().length() < 6 ){
                Common.showToast("password should be more than 6 character" );
            }else if(binding.etConfirmPasswordCreateAccount.getText().toString().length() < 6 && !binding.etConfirmPasswordCreateAccount.getText().toString().equals(binding.etPasswordCreateAccount.getText().toString())){
                Common.showToast("password not matched");
            }
            else{
                if (Common.isConnected()) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("name", binding.etUserNameCrateAccount.getText().toString());
                    map.put("email", binding.etEmailCrateAccount.getText().toString());
                    map.put("phone", binding.etPhoneCreateAccount.getText().toString());
                    map.put("password", binding.etPasswordCreateAccount.getText().toString());
                    map.put("password_confirmation", binding.etPasswordCreateAccount.getText().toString());

                    CreateAccountPresenter createAccountPresenter=new CreateAccountPresenter(this);
                    createAccountPresenter.registration(map);

                    Intent intent=new Intent(this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Common.showNoInternet();
                }

            }

        });
    }

    @Override
    public void onSuccessRegistration() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void ShowProgress() {

    }

    @Override
    public void dismissProgress() {

    }
}