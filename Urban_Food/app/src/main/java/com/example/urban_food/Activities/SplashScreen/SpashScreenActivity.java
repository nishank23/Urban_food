package com.example.urban_food.Activities.SplashScreen;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.urban_food.Activities.Home.HomeNewActivity;
import com.example.urban_food.Activities.Login.LoginActivity;
import com.example.urban_food.Activities.Login.LoginActivityPresenter;
import com.example.urban_food.Activities.Login.LoginActivityView;
import com.example.urban_food.Activities.WelcomeScreen.WelcomeScreenActivity;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.Helper.PrefUtils;
import com.example.urban_food.databinding.ActivitySpashScreenBinding;
import com.example.urban_food.model.Address;
import com.example.urban_food.model.Cart;
import com.example.urban_food.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpashScreenActivity extends AppCompatActivity implements LoginActivityView,LocationListener {
    ActivitySpashScreenBinding binding;
    LoginActivityPresenter loginActivityPresenter = new LoginActivityPresenter(this);


    private String device_id;
    private String fcm_token;
    int location = 44;

    final String TAG = "GPS";
    private final static int ALL_PERMISSIONS_RESULT = 101;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    LocationManager locationManager;
    Location loc;
    ArrayList<String> permissions = new ArrayList<>();
    ArrayList<String> permissionsToRequest;
    ArrayList<String> permissionsRejected = new ArrayList<>();
    boolean isGPS = false;
    boolean isNetwork = false;
    boolean canGetLocation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDeviceIdAndToken();


        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
        isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);


        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
        isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkactivity();
            }
        }, 1000);

    }



    public void checkactivity() {
        if (PrefUtils.getBooleanPref(Common.isLoggedIn, this)) {
            //Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();

            if (PrefUtils.getStringPref(Common.userToken, this).isEmpty()) {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            } else {
                if (!isGPS && !isNetwork) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("device_type", "android");
                    map.put("device_id", device_id);
                    map.put("device_token", fcm_token);
                    loginActivityPresenter.getProfile(map);



                } else {
                    Log.d(TAG, "Connection on");
                    // check permissions
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (permissionsToRequest.size() > 0) {
                            requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                                    ALL_PERMISSIONS_RESULT);
                            Log.d(TAG, "Permission requests");
                            canGetLocation = false;
                        }
                    }
                    // get location
                    getLocation();

                }

            }

        } else {
            startActivity(new Intent(this, WelcomeScreenActivity.class));
            finish();
        }

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
    public void onSuccessProfile(List<Cart> cardItemlist, List<Address> addressesItemList, User user) {
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

    private void getLocation() {
        try {
            if (canGetLocation) {
                Log.d(TAG, "Can get location");
                if (isGPS) {
                    // from GPS
                    Log.d(TAG, "GPS on");
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                        if (loc != null){
                            updateUI(loc);
                        }
                    }
                } else if (isNetwork) {
                    // from Network Provider
                    Log.d(TAG, "NETWORK_PROVIDER on");
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (loc != null){
                            GlobalData.latitude=loc.getLatitude();
                            GlobalData.longitude=loc.getLongitude();
                            updateUI(loc);
                        }
                    }
                } else {
                    loc.setLatitude(0);
                    loc.setLongitude(0);
                    updateUI(loc);
                }
            } else {
                Log.d(TAG, "Can't get location");
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void getLastLocation() {
        try {
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, false);
            Location location = locationManager.getLastKnownLocation(provider);
            Log.d(TAG, provider);
            Log.d(TAG, location == null ? "NO LastLocation" : location.toString());
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }


    private void updateUI(Location loc) {
        Log.d(TAG, "updateUI");
        GlobalData.latitude=loc.getLatitude();
        GlobalData.longitude=loc.getLongitude();

        HashMap<String, String> map = new HashMap<>();
        map.put("device_type", "android");
        map.put("device_id", device_id);
        map.put("device_token", fcm_token);
        loginActivityPresenter.getProfile(map);
        Log.d("values","lat:"+GlobalData.latitude+" long:"+GlobalData.longitude);
    }


    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT:
                Log.d(TAG, "onRequestPermissionsResult");
                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(permissionsRejected.toArray(
                                                        new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }
                } else {
                    Log.d(TAG, "No rejected permissions.");
                    canGetLocation = true;
                    getLocation();
                }
                break;
        }
    }
    private boolean hasPermission(String permission) {
        if (canAskPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }



    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.d(TAG, "onLocationChanged");
        updateUI(location);
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        try{
            LocationListener.super.onProviderDisabled(provider);
        }
        catch (SecurityException e){

        }
    }

}