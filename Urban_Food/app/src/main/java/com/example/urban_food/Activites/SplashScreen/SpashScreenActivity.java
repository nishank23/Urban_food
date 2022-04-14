package com.example.urban_food.Activites.SplashScreen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.core.location.LocationManagerCompat;

import com.example.urban_food.Activites.Home.HomeActivity;
import com.example.urban_food.Activites.Login.LoginActivity;
import com.example.urban_food.Activites.Login.LoginActivityPresenter;
import com.example.urban_food.Activites.Login.LoginActivityView;
import com.example.urban_food.Activites.WelcomeScreen.WelcomeScreenActivity;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.PrefUtils;
import com.example.urban_food.Modal.ProfileModal.AddressesItem;
import com.example.urban_food.Modal.ProfileModal.CartItem;
import com.example.urban_food.databinding.ActivitySpashScreenBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.List;

public class SpashScreenActivity extends AppCompatActivity implements LoginActivityView {
    ActivitySpashScreenBinding binding;
    LoginActivityPresenter loginActivityPresenter = new LoginActivityPresenter(this);


    private String device_id;
    private String fcm_token;
    double latitude;
    double longitude;
    int location = 44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDeviceIdAndToken();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkactivity();
            }
        }, 3000);

    }

    public void checkactivity() {
        if (PrefUtils.getBooleanPref(Common.isLoggedIn, this)) {
            //Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();
            if (PrefUtils.getStringPref(Common.userToken, this).isEmpty()) {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            } else {
                getLocation();
            }

        } else {
            startActivity(new Intent(this, WelcomeScreenActivity.class));
            finish();
        }

    }

    private void getLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PermissionChecker.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PermissionChecker.PERMISSION_GRANTED) {
            if (isLocationEnabled(this)) {
                FusedLocationProviderClient fusedLocationObj = LocationServices.getFusedLocationProviderClient(this);
                fusedLocationObj.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();

                            HashMap<String, String> map = new HashMap<>();
                            map.put("device_type", "android");
                            map.put("device_id", device_id);
                            map.put("device_token", fcm_token);
                            loginActivityPresenter.getProfile(map);
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            /*HashMap<String, String> map = new HashMap<>();
            map.put("device_type", "android");
            map.put("device_id", device_id);
            map.put("device_token", fcm_token);
            loginActivityPresenter.getProfile(map);*/
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, location);
        }

    }


    public boolean isLocationEnabled(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return manager != null && LocationManagerCompat.isLocationEnabled(manager);
    }


    private void getDeviceIdAndToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("LoginActivity", "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token

                        // Log and toast
                        fcm_token = task.getResult();

                    }
                });

        try {
            device_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            device_id = "";
        }


    }

    @Override
    public void onSuccessLogin(String token) {

    }

    @Override
    public void onSuccessProfile(List<CartItem> cardItemlist, List<AddressesItem> addressesItemList) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onError(String msg) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void ShowProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == location) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            }
        }
    }

}