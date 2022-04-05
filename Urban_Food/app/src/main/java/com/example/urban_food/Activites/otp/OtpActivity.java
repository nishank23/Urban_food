package com.example.urban_food.Activites.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.urban_food.Activites.createAccount.CreateAccount;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityOtpBinding;

import java.text.DecimalFormat;

public class OtpActivity extends AppCompatActivity implements OtpView {
    ActivityOtpBinding binding;
    String phoneData;
    OtpPresenter presenter=new OtpPresenter(this);
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityOtpBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        presenter.callApiOtp(phoneData);

        countDownTimer = new CountDownTimer(60000, 1000){
            @Override
            public void onTick(long l) {
                long sec = (l / 1000) % 60;
                binding.tvResendOtp.setText(new DecimalFormat("00").format(sec));
                binding.tvResendOtp.setEnabled(false);
            }

            @Override
            public void onFinish() {
                binding.tvResendOtp.setEnabled(true);
                binding.tvResendOtp.setText(getString(R.string.resend_otp));
            }
        };
        countDownTimer.start();

        binding.buttonNextOtp.setOnClickListener(view -> {
            if(){
                startActivity(new Intent(this, CreateAccount.class));
            }
        });

    }

    @Override
    public void onSuccessOtp(int data) {
        phoneData=String.valueOf(data);
    }

    @Override
    public void onError() {
        Common.showSomethingWentWrong(this);
    }

    @Override
    public void ShowProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }
}