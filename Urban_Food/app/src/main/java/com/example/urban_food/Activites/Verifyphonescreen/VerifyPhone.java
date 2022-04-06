package com.example.urban_food.Activites.Verifyphonescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.urban_food.Activites.otp.OtpActivity;
import com.example.urban_food.Activites.otp.OtpPresenter;
import com.example.urban_food.Activites.otp.OtpView;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityVerifyphoneBinding;

public class verifyphone extends AppCompatActivity implements VerifyphoneView {
    ActivityVerifyphoneBinding binding;
    VerifyphoneView view;
    String otpData = "111111";
    String checker = "";
    String phone = "";
    VerifyphonePresenter presenter = new VerifyphonePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityVerifyphoneBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        checker=getIntent().getStringExtra("checker");
        binding.btnNext.setOnClickListener(view -> {
            if (binding.etPhoneNo.getText().toString().length() == 10) {

                        phone=binding.etPhoneNo.getText().toString();
/*
                presenter.callApiOtp("+91" + binding.etPhoneNo.getText().toString());
 */
                if (!otpData.isEmpty()) {
                    Intent intent = new Intent(this, OtpActivity.class);
                    intent.putExtra("otpNo", otpData);
                    intent.putExtra("checker", checker);
                    intent.putExtra("phone",phone);
                    startActivity(intent);
                } else {
                    Common.showSomethingWentWrong(this);
                }
            } else {
                Common.showToast(this, "Phone number is not valid");
            }
        });
    }

    @Override
    public void onSuccessOtp(int data) {
        otpData = String.valueOf(data);
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