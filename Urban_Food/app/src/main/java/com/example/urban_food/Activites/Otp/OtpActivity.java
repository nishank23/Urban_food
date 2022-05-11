package com.example.urban_food.Activites.Otp;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.urban_food.Activites.CreateAccount.CreateAccount;
import com.example.urban_food.Activites.ForgetPasswordScreen.Forgetpassword;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityOtpBinding;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OtpActivity extends AppCompatActivity implements OtpView {
    private static final int REQ_USER_CONSENT = 200;
    ActivityOtpBinding binding;
    String otpData = "";
    boolean checker = false;
    String phoneString="";
    String userid="";
    CountDownTimer countDownTimer;
    SmsBroadcastReceiver smsBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        SmsRetrieverClient client = SmsRetriever.getClient(this);
        client.startSmsUserConsent(null);


        otpData = getIntent().getStringExtra("otpNo");
        checker = getIntent().getBooleanExtra("checker",false);
        phoneString=getIntent().getStringExtra("phone");

        countDownTimer = new CountDownTimer(60000, 1000) {
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

            if (!binding.etOtp.getText().toString().isEmpty()) {
                if (checker) {
                    if (binding.etOtp.getText().toString().equals(otpData)) {
                        Intent intent=new Intent(this,CreateAccount.class);
                        intent.putExtra("phone",phoneString);
                        startActivity(intent);

                    } else {
                        Common.showToast("Otp is not valid");
                    }
                } else {
                    if (binding.etOtp.getText().toString().equals(otpData)) {

                        Intent intent=new Intent(this,Forgetpassword.class);
                        startActivity(intent);
                        intent.putExtra("userid",userid);
                    } else {
                        Common.showToast("Otp is not valid");
                    }
                }
            } else {
                Common.showToast("Otp is not valid");
            }


        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_USER_CONSENT) {

            if ((resultCode == RESULT_OK) && (data != null)) {

                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                getOtpFromMessage(message);
            }
        }
    }

    private void getOtpFromMessage(String message) {

        Pattern otpPattern = Pattern.compile("(|^)\\d{6}");
        Matcher matcher = otpPattern.matcher(message);
        if (matcher.find()) {

            binding.etOtp.setText(matcher.group(0));

        }


    }

    private void registerBroadcastReceiver() {

        smsBroadcastReceiver = new SmsBroadcastReceiver();

        smsBroadcastReceiver.smsBroadcastReceiverListener = new SmsBroadcastReceiver.SmsBroadcastReceiverListener() {
            @Override
            public void onSuccess(Intent intent) {

                startActivityForResult(intent, REQ_USER_CONSENT);

            }

            @Override
            public void onFailure() {

            }
        };

        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        registerReceiver(smsBroadcastReceiver, intentFilter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        registerBroadcastReceiver();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
        unregisterReceiver(smsBroadcastReceiver);

    }

    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
        unregisterReceiver(smsBroadcastReceiver);

    }
}