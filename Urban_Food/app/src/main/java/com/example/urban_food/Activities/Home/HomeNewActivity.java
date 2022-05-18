package com.example.urban_food.Activities.Home;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.urban_food.Activities.SplashScreen.SpalshInterface;
import com.example.urban_food.Adapter.HomeBottomSheetAdapter;
import com.example.urban_food.Adapter.HomeViewPager;
import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Api.ApiInterface;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityHomeBinding;
import com.example.urban_food.databinding.BottomsheetHomeLayoutBinding;
import com.example.urban_food.fragment.explore.Explore;
import com.example.urban_food.fragment.favorite.Favorite;
import com.example.urban_food.fragment.myorder.MyOrder;
import com.example.urban_food.fragment.profile.Profile;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeNewActivity extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    ActivityHomeBinding binding;
    ArrayList<Fragment> fragments;


    // LogCat tag
    private final static int PLAY_SERVICES_REQUEST = 1000;
    private final static int REQUEST_CHECK_SETTINGS = 2000;
    private final static int ADDRESS_SELECTION = 1;
    private static final int ASK_MULTIPLE_PERMISSION_REQUEST_CODE = 0;
    public static double latitude;
    public static double longitude;
    LocationRequest mLocationRequest;
    FusedLocationProviderClient mFusedLocationClient;
    Retrofit retrofit;
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private String TAG = HomeNewActivity.class.getSimpleName();
    ApiInterface locationInterface = ApiClient.getRetrofit();
    private BottomSheetDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (isLocationEnabled()) {
            setAdapter();
        } else {
            showSelectAddressDialog();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        mGoogleApiClient.connect();

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(30 * 1000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setSmallestDisplacement(200);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult locationSettingsResult) {

                final Status status = locationSettingsResult.getStatus();

                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location requests here
                        getLocation();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(HomeNewActivity.this, REQUEST_CHECK_SETTINGS);

                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case ASK_MULTIPLE_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean FINE_LOCATIONPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean COARSE_LOCATIONPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (FINE_LOCATIONPermission && COARSE_LOCATIONPermission) {
                        if (isLocationEnabled()) {
                            getLocation();
                        } else {
                            Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }

                    } else {
                        Snackbar.make(this.findViewById(android.R.id.content),
                                "Please Grant Permissions to start service",
                                Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ActivityCompat.requestPermissions(HomeNewActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, ASK_MULTIPLE_PERMISSION_REQUEST_CODE);
                                    }
                                }).show();
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // All required changes were successfully made
                        getLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        break;
                    default:
                        break;
                }
                break;
            case ADDRESS_SELECTION:
                dialog.dismiss();
                if (GlobalData.Address.size() > 0) {
                    //setViewPager

                    setAdapter();
                } else {
                    showSelectAddressDialog();
                }
                break;
            case 20:
                if (isLocationEnabled()) {

                    buildGoogleApiClient();
                } else {
                    showSelectAddressDialog();
                }
                break;
        }
    }

    private void showSelectAddressDialog() {

        //bottomSheet
        dialog = new BottomSheetDialog(this);
        BottomsheetHomeLayoutBinding bottomsheetHomeLayoutBinding = BottomsheetHomeLayoutBinding.inflate(getLayoutInflater());
        dialog.setContentView(bottomsheetHomeLayoutBinding.getRoot());
        HomeBottomSheetAdapter adapter = new HomeBottomSheetAdapter(this, GlobalData.Address, new SpalshInterface() {
            @Override
            public void passer(boolean value) {
                setAdapter();
                dialog.dismiss();
            }
        });
        bottomsheetHomeLayoutBinding.recyclerViewBottom.setAdapter(adapter);
        bottomsheetHomeLayoutBinding.recyclerViewBottom.setLayoutManager(new LinearLayoutManager(this));
        bottomsheetHomeLayoutBinding.btnGrant.setOnClickListener(view -> {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 20);
            //SharedHelper.putbooleanKey(HomeActivity.this, GlobalData.isLocationOn, true);
            dialog.dismiss();
        });

        dialog.setCancelable(false);
        dialog.show();

    }

    private void setAdapter() {

        fragments = new ArrayList<>();
        fragments.add(new Explore());
        fragments.add(new Favorite());
        fragments.add(new MyOrder());
        fragments.add(new Profile());

        HomeViewPager adapter = new HomeViewPager(this, fragments);
        binding.homeViewpager.setAdapter(adapter);
        binding.homeViewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);


        binding.bottomBar.setupWithViewPager2(binding.homeViewpager);

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        latitude = mLastLocation.getLatitude();
        longitude = mLastLocation.getLongitude();
        GlobalData.latitude = mLastLocation.getLatitude();
        GlobalData.longitude = mLastLocation.getLongitude();
        GlobalData.latitudeC = mLastLocation.getLatitude();
        GlobalData.longitudeC = mLastLocation.getLongitude();
        Log.e("latitude2", "" + mLastLocation.getLatitude());
        Log.e("longitude2", "" + mLastLocation.getLongitude());
        Log.e("GlobalData.latitude2", "" + GlobalData.latitude);
        Log.e("GlobalData.longitude2 ", "" + GlobalData.longitude);
        getAddress();
    }

    private void getLocation() {
        try {
            mLastLocation = LocationServices.FusedLocationApi
                    .getLastLocation(mGoogleApiClient);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        if (mLastLocation == null) {
            mLocationRequest = new LocationRequest();
            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(30 * 1000);
            mLocationRequest.setFastestInterval(5 * 1000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            mLocationRequest.setSmallestDisplacement(200);
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

            }
        } else {
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
            GlobalData.latitude = mLastLocation.getLatitude();
            GlobalData.longitude = mLastLocation.getLongitude();
            GlobalData.latitudeC = mLastLocation.getLatitude();
            GlobalData.longitudeC = mLastLocation.getLongitude();
            /*Log.e("latitude", "" + mLastLocation.getLatitude());
            Log.e("longitude", "" + mLastLocation.getLongitude());
            Log.e("GlobalData.latitude", "" + GlobalData.latitude);
            Log.e("GlobalData.longitude ", "" + GlobalData.longitude);*/
            getAddress();
        }

    }

    public void getAddress() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/geocode/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        locationInterface = retrofit.create(ApiInterface.class);
        Call<ResponseBody> call = locationInterface.getResponse(latitude + "," + longitude,
                getString(R.string.google_api_key1));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("sUCESS", "SUCESS" + response.body());
                if (response.body() != null) {
                    //BroadCast Listner
                    Intent intent = new Intent("location");
                    // You can also include some extra data.
                    intent.putExtra("message", "This is my message!");
                    LocalBroadcastManager.getInstance(HomeNewActivity.this).sendBroadcast(intent);

                    try {
                        String bodyString = new String(response.body().bytes());
                        Log.e("sUCESS", "bodyString" + bodyString);
                        JSONObject jsonObj = null;
                        try {
                            jsonObj = new JSONObject(bodyString);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONArray jsonArray = jsonObj.optJSONArray("results");
                        if (jsonArray.length() > 0) {
                            if (GlobalData.addressHeader.equalsIgnoreCase("")) {
                                GlobalData.addressHeader = jsonArray.optJSONObject(0).optString("formatted_address");
                                GlobalData.address = jsonArray.optJSONObject(0).optString("formatted_address");
                                Log.v("Formatted Address", "" + GlobalData.addressHeader);
                            }
                        } else {
                            if (GlobalData.addressHeader.equalsIgnoreCase("")) {
                                GlobalData.addressHeader = "" + latitude + "" + longitude;
                                GlobalData.address = "" + latitude + "" + longitude;
                            }
                        }

                        //open viewpager
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                } else {
                    GlobalData.addressHeader = "" + latitude + "" + longitude;
                    GlobalData.address = "" + latitude + "" + longitude;
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("onFailure", "onFailure" + call.request().url());
            }
        });

    }
}
