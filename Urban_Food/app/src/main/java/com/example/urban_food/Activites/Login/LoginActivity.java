package com.example.urban_food.Activites.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.example.urban_food.Helper.Common;
import com.example.urban_food.R;

public class LoginActivity extends AppCompatActivity {

    private final Activity activity = LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Common.isConnected(activity)){

        }else {
            Common.showNoInternet(activity);
        }
    }
}