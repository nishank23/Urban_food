package com.example.urban_food.Activities.SplashScreen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.urban_food.Activities.Home.HomeNewActivity;
import com.example.urban_food.Activities.Login.LoginActivity;
import com.example.urban_food.Activities.Login.LoginActivityPresenter;
import com.example.urban_food.Activities.Login.LoginActivityView;
import com.example.urban_food.Activities.WelcomeScreen.WelcomeScreenActivity;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.Helper.PrefUtils;
import com.example.urban_food.databinding.ActivitySpashScreenBinding;
import com.example.urban_food.model.Cart;
import com.example.urban_food.model.User;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class SplashActivity extends AppCompatActivity implements LoginActivityView {
    ActivitySpashScreenBinding binding;
    FusedLocationProviderClient mFusedLocationClient;
    final int PERMISSION_ID = 44;
    LoginActivityPresenter loginActivityPresenter = new LoginActivityPresenter(this);

    double latitude1 = 0;
    double longitude1 = 0;

    private String device_id;
    private String fcm_token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getDeviceIdAndToken();

        // method to get the location
        getLastLocation();

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


    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            latitude1 = location.getLatitude();
                            longitude1 = location.getLongitude();

                            GlobalData.latitude = location.getLatitude();
                            GlobalData.longitude = location.getLongitude();
                            GlobalData.latitudeC = location.getLatitude();
                            GlobalData.longitudeC = location.getLongitude();
                            getAddress(latitude1, longitude1);
                        }
                    }
                });
            } else {
                /*Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);*/
                goToNextScreen();
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latitude1 = mLastLocation.getLatitude();
            longitude1 = mLastLocation.getLongitude();
            GlobalData.latitude = mLastLocation.getLatitude();
            GlobalData.longitude = mLastLocation.getLongitude();
            GlobalData.latitudeC = mLastLocation.getLatitude();
            GlobalData.longitudeC = mLastLocation.getLongitude();
            getAddress(latitude1, longitude1);
        }
    };

    // method to check for permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE}, PERMISSION_ID);
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkPermissions()) {
/*
            getLastLocation();
*/
        }
    }

    public void getAddress(double lat, double lng) {
        runOnUiThread(() -> {
            Geocoder geocoder = new Geocoder(SplashActivity.this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
                if (addresses != null) {
                    try {
                        Address obj = addresses.get(0);
                        if (GlobalData.addressHeader.equalsIgnoreCase("")) {
                            GlobalData.addressHeader = obj.getAddressLine(0);
                            GlobalData.address = obj.getAddressLine(0);
                            Log.v("Formatted Address", "" + GlobalData.addressHeader);
                            com.example.urban_food.model.Address currAdd = new com.example.urban_food.model.Address();
                            currAdd.setMapAddress(obj.getAddressLine(0));
                            currAdd.setLatitude(latitude1);
                            currAdd.setLongitude(longitude1);
                            GlobalData.userAddressSelect = currAdd;

                        }

                        goToNextScreen();
                    } catch (Exception e) {
                        if (GlobalData.addressHeader.equalsIgnoreCase("")) {
                            GlobalData.addressHeader = "" +GlobalData.latitude + "" + GlobalData.longitude;
                            GlobalData.address = "" + GlobalData.latitude + "" + GlobalData.longitude;
                            com.example.urban_food.model.Address currAdd = new com.example.urban_food.model.Address();
                            currAdd.setMapAddress(GlobalData.latitude + "" + GlobalData.longitude);
                            currAdd.setLatitude(latitude1);
                            currAdd.setLongitude(longitude1);
                            GlobalData.userAddressSelect = currAdd;
                        }
                        goToNextScreen();
                        e.printStackTrace();
                    }
                } else {
                    if (GlobalData.addressHeader.equalsIgnoreCase("")) {
                        GlobalData.addressHeader = "" + GlobalData.latitude + "" + GlobalData.longitude;
                        GlobalData.address = "" + GlobalData.latitude + "" + GlobalData.longitude;
                        com.example.urban_food.model.Address currAdd = new com.example.urban_food.model.Address();
                        currAdd.setMapAddress(GlobalData.latitude + "" + GlobalData.longitude);
                        currAdd.setLatitude(latitude1);
                        currAdd.setLongitude(longitude1);
                        GlobalData.userAddressSelect = currAdd;
                    }
                    goToNextScreen();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        });
    }

    private void goToNextScreen() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjVjNTFmNDVkMzNjYzg1MDYyNDg1ZmVkYTg2MzUwYWJmZjg0OTFlNWI3OGI5ZDhkN2ZlOTg0ZDQzMDk5NDRkMmZjZmQ2MTk0NDU3ZjI0OGM1In0.eyJhdWQiOiIyIiwianRpIjoiNWM1MWY0NWQzM2NjODUwNjI0ODVmZWRhODYzNTBhYmZmODQ5MWU1Yjc4YjlkOGQ3ZmU5ODRkNDMwOTk0NGQyZmNmZDYxOTQ0NTdmMjQ4YzUiLCJpYXQiOjE2MjQ5NjYwODgsIm5iZiI6MTYyNDk2NjA4OCwiZXhwIjoxNjU2NTAyMDg4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.foIj5twC0shOvGWhhs7UivrpxbKDSFg6KtonloWXLOnYg1vIrxVBQfmXBBCRgKe0Ki4NGvw_FVbYxE_wYmvjvyode5NntzSCpG6SeZj__G8jscAfIds8BJ1JdLugCgA7gaWJ6pkj1Jpiz1t4kYjQ0gZYQvoQvIrzHsr_fkVQf1S27AKDbnaWAqEpCYW1pCPsQwZZgNOGEDQOp0pp5KQNErYd8bAh1guyBtUFI2STSaBOLR_lyp0a4WgAkbnmoAwkQrg6tt14jo2WLLPCjwcR64kVCP70N9KZGAq6o5GqR1uAbSOy6ohgQFX5yi_hdMPeWvet19H0AxuSylm-fvWa65e_EFVZwLy1ZRyWKyA6eu-LM89LltIgsBFusetw5bkwcvOq5a84dzN-eaNUBaQLwj5S_IpbYiwQuKKzlc2fz5Nt7Y7PwfKl9miCHTCig_PlYEyKHKJlhpev7AvMz0qq4tbcy4_jeWdEiSPXh2uJMzF2L8IoRNsX8-vEMR5Jf6G11Jj7bV96zdFfArQ3wVyqeyev-_MND76x9_Xex5GWGr4hw_ati-0_CRqgfaF-Gv8ZnINrsnrRTkmwIKBUzQUusuZlJ1pl3tJwi5DciRrJ508MDpN3f2_dxD8I1qQ5xLy2GQ_zLuSqcuHvvuSeinAt8ssGZN99vlb9DxNvP1QNn94";
            PrefUtils.putStringPref(Common.userToken, "Bearer " + token, this);


            if (PrefUtils.getBooleanPref(Common.isLoggedIn, this)) {


                if (PrefUtils.getStringPref(Common.userToken, this).isEmpty()) {
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("device_type", "android");
                    map.put("device_id", device_id);
                    map.put("device_token", fcm_token);
                    loginActivityPresenter.getProfile(map);


                }
            } else {
                startActivity(new Intent(this, WelcomeScreenActivity.class));
                finish();
            }
        },3000);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0) {
                boolean FINE_LOCATIONPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                boolean COARSE_LOCATIONPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (FINE_LOCATIONPermission && COARSE_LOCATIONPermission) {
                    getLastLocation();

                } else {
                    Snackbar.make(this.findViewById(android.R.id.content),
                            "Please Grant Permissions to start service",
                            Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_ID);
                                }
                            }).show();
                }
            }
        }
    }

    @Override
    public void onSuccessLogin(String token) {

    }

    @Override
    public void onSuccessProfile(List<Cart> cardItemlist, List<com.example.urban_food.model.Address> addressesItemList, User user) {
        GlobalData.Cart = cardItemlist;
        GlobalData.Address = addressesItemList;
        GlobalData.users=user;
        startActivity(new Intent(this, HomeNewActivity.class));
        finish();
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
}
