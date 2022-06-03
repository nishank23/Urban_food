package com.example.urban_food.Activities.ChangePassowrdScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.urban_food.databinding.ActivityChangePasswordBinding;

import java.util.HashMap;

public class ChangePassword extends AppCompatActivity implements ChangePasswordView {
    ActivityChangePasswordBinding binding;
    ChangePasswordPresenter changePasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        changePasswordPresenter = new ChangePasswordPresenter(this);
        setContentView(binding.getRoot());

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.btnCngpassword.setOnClickListener(view -> {

            if (binding.etOldpassword.getText().toString().isEmpty() && binding.etOldpassword.getText().length() <= 6) {
                Toast.makeText(this, "Enter old Password ", Toast.LENGTH_SHORT).show();
            }
            if (binding.etPassword.getText().toString().isEmpty() && binding.etConfirmpassword.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter New Password", Toast.LENGTH_SHORT).show();
            } else if (binding.etConfirmpassword.getText().toString().equals(binding.etPassword.getText().toString()) && !binding.etPassword.getText().toString().isEmpty() && !binding.etConfirmpassword.getText().toString().isEmpty()) {
                HashMap<String, String> map = new HashMap<>();
                map.put("password_old", binding.etOldpassword.getText().toString());
                map.put("password", binding.etPassword.getText().toString());
                map.put("password_confirmation", binding.etConfirmpassword.getText().toString());
                changePasswordPresenter.changePassword(map);
            }


        });

    }

    @Override
    public void onSuccessChange(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        onBackPressed();

    }

    @Override
    public void onError() {
        Toast.makeText(this, "Password is wrong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowProgress() {

    }

    @Override
    public void dismissProgress() {

    }
}