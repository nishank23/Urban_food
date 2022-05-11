package com.example.urban_food.Activites.ForgetPasswordScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.urban_food.Activites.Login.LoginActivity;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.databinding.ActivityForgetpasswordBinding;

import java.util.HashMap;

public class Forgetpassword extends AppCompatActivity implements ForgetpasswordView {
    ActivityForgetpasswordBinding binding;
    ForgetPasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetpasswordBinding.inflate(getLayoutInflater());
        presenter = new ForgetPasswordPresenter(this);
        setContentView(binding.getRoot());

        binding.btnCngpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etPassword.getText().toString().equals(binding.etConfirmPassword.getText().toString())) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("id", String.valueOf(GlobalData.users.getId()));
                    map.put("password", binding.etPassword.getText().toString());
                    map.put("password_confirmation", binding.etConfirmPassword.getText().toString());

                    presenter.resetPassword(map);
                }
            }
        });

    }

    @Override
    public void getSuccess(String msg) {
        if (!msg.isEmpty()) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
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