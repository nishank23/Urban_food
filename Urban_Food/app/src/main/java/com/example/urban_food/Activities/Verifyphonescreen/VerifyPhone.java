package com.example.urban_food.Activities.Verifyphonescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.urban_food.Activities.Otp.OtpActivity;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.databinding.ActivityVerifyphoneBinding;
import com.example.urban_food.model.ForgotPassword;

public class VerifyPhone extends AppCompatActivity implements VerifyPhoneView {
    ActivityVerifyphoneBinding binding;
    VerifyPhoneView view;
    String otpData = "111111";
    boolean checker = false;
    String phone = "";
    String userid="";
    VerifyPhonePresenter presenter = new VerifyPhonePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityVerifyphoneBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        checker=getIntent().getBooleanExtra("checker", false);
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.btnNext.setOnClickListener(view -> {
            if (binding.etPhoneNo.getText().toString().length() == 10) {

                phone=binding.etPhoneNo.getText().toString();

                if(checker){
/*
                    presenter.callApiOtp("+91" + binding.etPhoneNo.getText().toString());
*/
                    presenter.callApiOtp("+91" +binding.etPhoneNo.getText().toString());

                }else {
                    presenter.forgetpassword("+91"+binding.etPhoneNo.getText().toString());

                }

            } else {
                Common.showToast( "Phone number is not valid");
            }
        });
    }

    @Override
    public void onSuccessOtp(int data) {
        otpData = String.valueOf(data);
        if (!otpData.isEmpty()) {
            Intent intent = new Intent(this, OtpActivity.class);
            intent.putExtra("otpNo", otpData);

            intent.putExtra("checker", checker);
            intent.putExtra("phone",phone);
            startActivity(intent);
        } else {
            Common.showSomethingWentWrong();
        }
    }

    @Override
    public void onSucuessForget(ForgotPassword data) {
        otpData = data.getUser().getOtp();
        GlobalData.users = data.getUser();
        if (!otpData.isEmpty()) {
            Intent intent = new Intent(this, OtpActivity.class);
            intent.putExtra("otpNo", otpData);
            intent.putExtra("checker", checker);
            intent.putExtra("phone",phone);
            startActivity(intent);
        } else {
            Common.showSomethingWentWrong();
        }

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