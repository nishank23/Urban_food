package com.example.urban_food.Activites.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.example.urban_food.Activites.Login.LoginActivityPresenter;
import com.example.urban_food.Activites.SplashScreen.SpalshInterface;
import com.example.urban_food.Adapter.HomeBottomSheetAdapter;
import com.example.urban_food.Adapter.HomeViewPager;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.Helper.PrefUtils;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityHomeBinding;
import com.example.urban_food.databinding.BottomsheetHomeLayoutBinding;
import com.example.urban_food.fragment.explore.Explore;
import com.example.urban_food.fragment.favorite.Favorite;
import com.example.urban_food.fragment.myorder.MyOrder;
import com.example.urban_food.fragment.profile.Profile;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements LocationListener {
    ActivityHomeBinding binding;

    boolean bottomClickChecker=false;
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
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Explore());
        fragments.add(new Favorite());
        fragments.add(new MyOrder());
        fragments.add(new Profile());

        ArrayList<Integer> icons = new ArrayList<>();
        icons.add(R.drawable.ic_explore);
        icons.add(R.drawable.ic_favroite);
        icons.add(R.drawable.ic_myorder);
        icons.add(R.drawable.ic_myprofile);

        ArrayList<String> texts = new ArrayList<>();
        texts.add("Explore");
        texts.add("Favorite");
        texts.add("MyOrder");
        texts.add("Profile");

        HomeViewPager adapter = new HomeViewPager(this, fragments);
        binding.homeViewpager.setAdapter(adapter);
        binding.homeViewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        new TabLayoutMediator(binding.homeTab, binding.homeViewpager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setIcon(icons.get(position));
                tab.setText(texts.get(position));
            }
        }).attach();

        if (!isGPS && !isNetwork) {
            bottom();

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
    @Override
    protected void onResume() {
        super.onResume();
        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
        isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);
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
    void bottom(){
        //bottomSheet
        BottomSheetDialog dialog=new BottomSheetDialog(this);
        BottomsheetHomeLayoutBinding bottomsheetHomeLayoutBinding=BottomsheetHomeLayoutBinding.inflate(getLayoutInflater());
        dialog.setContentView(bottomsheetHomeLayoutBinding.getRoot());
        HomeBottomSheetAdapter adapter=new HomeBottomSheetAdapter(this, GlobalData.Address, new SpalshInterface() {
            @Override
            public void passer(boolean value) {
                bottomClickChecker=value;
            }
        });
        bottomsheetHomeLayoutBinding.recyclerViewBottom.setAdapter(adapter);
        bottomsheetHomeLayoutBinding.recyclerViewBottom.setLayoutManager(new LinearLayoutManager(this));
        bottomsheetHomeLayoutBinding.btnGrant.setOnClickListener(view -> {
            // check permissions
            showSettingsAlert();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (permissionsToRequest.size() > 0) {
                    requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                            ALL_PERMISSIONS_RESULT);
                    Log.d(TAG, "Permission requests");
                    canGetLocation = false;

                    getLocation();
                    dialog.dismiss();
                }
            }
        });
        if(bottomClickChecker){
            dialog.dismiss();
        }
        dialog.setCancelable(false);
        dialog.show();
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
                        loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (loc != null)
                            updateUI(loc);
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
                        if (loc != null)
                            updateUI(loc);
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
    private void updateUI(Location loc) {
        Log.d(TAG, "updateUI");
        GlobalData.latitude=loc.getLatitude();
        GlobalData.longitude=loc.getLongitude();

    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("GPS is not Enabled!");
        alertDialog.setMessage("Do you want to turn on GPS?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
/*
        PrefUtils.putBooleanPref(Common.isLoggedIn,true,this);
*/

    }


    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

}