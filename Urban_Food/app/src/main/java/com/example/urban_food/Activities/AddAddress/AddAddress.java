package com.example.urban_food.Activities.AddAddress;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Slide;
import androidx.transition.TransitionManager;

import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityAddAddressBinding;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class AddAddress extends AppCompatActivity implements AddAddressView, OnMapReadyCallback, LocationListener, GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraIdleListener {
    ActivityAddAddressBinding binding;
    AddAddressPresenter addAddressPresenter;

    private static final int AUTOCOMPLETE_REQUEST_CODE = 123;
    private GoogleMap mMap;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    String checkedChip = "";
    Activity activity = AddAddress.this;
    double lat, lon;
    String strAdd = "";
    PlacesClient placesClient;
    String city = "", state = "", country = "", pincode = "";
    boolean isEdit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        addAddressPresenter = new AddAddressPresenter(this);

        Places.initialize(getApplicationContext(), getString(R.string.google_api_key1));
        placesClient = Places.createClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (isEdit) {
            binding.btnlogin.setText("Update Address");
            binding.etHouse.setText(GlobalData.editAddress.getBuilding());
            binding.etAddress.setText(GlobalData.editAddress.getMapAddress());
            binding.etLandmark.setText(GlobalData.editAddress.getLandmark());
            if (GlobalData.editAddress.getType().equalsIgnoreCase("work")) {

                binding.RbWork.setChecked(true);
            } else if (GlobalData.editAddress.getType().equalsIgnoreCase("home")) {
                binding.RbHome.setChecked(true);
            } else {
                binding.llType.setVisibility(View.GONE);
                binding.llOther.setVisibility(View.VISIBLE);
                binding.etOther.setText(GlobalData.editAddress.getType());
            }
        } else {
            binding.etHouse.setText("");
            binding.etAddress.setText("");
            binding.etLandmark.setText("");
            binding.RbWork.setChecked(false);
            binding.RbHome.setChecked(false);

            binding.llType.setVisibility(View.VISIBLE);
            binding.llOther.setVisibility(View.GONE);


        }


        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAutoCompleteIntent();
            }
        });

        binding.RbHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkedChip = binding.RbHome.getText().toString();
                }
            }
        });

        binding.RbWork.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkedChip = binding.RbWork.getText().toString();
                }
            }
        });

        binding.RbOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.llType.setVisibility(View.GONE);
                    Slide slide = new Slide();
                    slide.setSlideEdge(Gravity.END);
                    TransitionManager.beginDelayedTransition(binding.llOther, slide);
                    Slide slide1 = new Slide();
                    slide1.setSlideEdge(Gravity.START);
                    TransitionManager.beginDelayedTransition(binding.llType, slide1);
                    binding.llOther.setVisibility(View.VISIBLE);
                    binding.etOther.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            checkedChip = binding.etOther.getText().toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            }
        });

        binding.tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.llOther.setVisibility(View.GONE);
                Slide slide = new Slide();
                slide.setSlideEdge(Gravity.START);
                TransitionManager.beginDelayedTransition(binding.llType, slide);
                binding.llType.setVisibility(View.VISIBLE);

                binding.RbOther.setChecked(false);
            }
        });


        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEdit) {
                    //call patchapi for address with map

                    com.example.urban_food.model.Address address = new com.example.urban_food.model.Address();


                    address.setBuilding(binding.etHouse.getText().toString());

                    address.setCity(city);

                    address.setState(state);

                    address.setCountry(country);

                    address.setPincode(pincode);
                    address.setLandmark(binding.etLandmark.getText().toString());
                    address.setMapAddress(binding.etAddress.getText().toString());
                    address.setLatitude(lat);
                    address.setLongitude(lon);

                    if (binding.RbHome.isChecked())
                        address.setType("home");
                    else if (binding.RbWork.isChecked())
                        address.setType("work");
                    else
                        address.setType(binding.etOther.getText().toString());

                    addAddressPresenter.addAddress(address);

                } else {
                    com.example.urban_food.model.Address address = new com.example.urban_food.model.Address();

                    address.setUserId(GlobalData.users.getId());
                    address.setId(GlobalData.editAddress.getId());

                    address.setBuilding(binding.etHouse.getText().toString());

                    address.setCity(GlobalData.editAddress.getCity());

                    address.setState(GlobalData.editAddress.getState());

                    address.setCountry(GlobalData.editAddress.getCountry());

                    address.setPincode(GlobalData.editAddress.getPincode());
                    address.setLandmark(binding.etLandmark.getText().toString());
                    address.setMapAddress(binding.etAddress.getText().toString());
                    address.setLatitude(GlobalData.latitude);
                    address.setLongitude(GlobalData.longitude);

                    if (binding.RbHome.isChecked())
                        address.setType("home");
                    else if (binding.RbWork.isChecked())
                        address.setType("work");
                    else
                        address.setType(binding.etOther.getText().toString());
                    addAddressPresenter.updateAddress(GlobalData.editAddress.getId(),address);
                }


            }
        });

    }


    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {

        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                if (addresses.size() > 0) {
                    Address returnedAddress = addresses.get(0);
                    StringBuilder strReturnedAddress = new StringBuilder("");

                    Log.d("whole_address", returnedAddress.toString());

                    for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                        strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                    }
                    strAdd = strReturnedAddress.toString();
                    Log.w("MyCurrentloctionaddress", strReturnedAddress.toString());
                    ////ToastGone.makeText(requireActivity(), ""+addresses.get(0).getLocality()+":"+addresses.get(0).getAdminArea(), //ToastGone.LENGTH_SHORT).show();

                }

            } else {
                Log.w("MyCurrentloctionaddress", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("MyCurrentloctionaddress", "Canont get Address!");
        }
        return strAdd;
    }

    private void createAutoCompleteIntent() {

        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS);
        Intent i = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .build(AddAddress.this);
        startActivityForResult(i, AUTOCOMPLETE_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                binding.tvCurrentAdd.setText(place.getAddress());
                binding.etAddress.setText(place.getAddress());
                binding.etHouse.setText(" ");
                binding.etLandmark.setText(" ");
                LatLng latLng = place.getLatLng();

                getPlace(place.getId());

                if (latLng != null) {
                    GlobalData.latitude = latLng.latitude;
                    GlobalData.longitude = latLng.longitude;

                    if (mCurrLocationMarker != null) {
                        mCurrLocationMarker.remove();
                        mCurrLocationMarker = mMap.addMarker(new MarkerOptions().position(latLng));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

                    }
                }

            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.d("tag_result", status.getStatusMessage());

            }
        }
    }

    public void getPlace(String id) {
        List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS);

        // Construct a request object, passing the place ID and fields array.
        FetchPlaceRequest request = FetchPlaceRequest.builder(id, placeFields).build();

        placesClient.fetchPlace(request).addOnSuccessListener((response) -> {
            Place place = response.getPlace();
            binding.tvCurrentAdd.setText(place.getAddress());
            LatLng latLng = place.getLatLng();
            lat = latLng.latitude;
            lon = latLng.longitude;
            getAddress(lat, lon);
            CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(18).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            Log.i("ads", "Place found: " + place.getName());
        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                int statusCode = apiException.getStatusCode();
                // Handle error with given status code.
                Log.e("Abc", "Place not found: " + exception.getMessage());
            }
        });
    }

    public void getAddress(double lat, double lng) {
        runOnUiThread(() -> {
            Geocoder geocoder = new Geocoder(AddAddress.this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
                if (addresses != null) {
                    try {
                        Address obj = addresses.get(0);
                        binding.etAddress.setText(obj.getAddressLine(0));
                        /*getCompleteAddressString(lat,lon);*/
                        Address returnedAddress = addresses.get(0);
                        StringBuilder strReturnedAddress = new StringBuilder("");


                        city = returnedAddress.getLocality();
                        pincode = returnedAddress.getPostalCode();
                        state = returnedAddress.getAdminArea();
                        country = returnedAddress.getCountryName();
                        Log.d("whole_address", pincode);

                        for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                            strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                        }
                        strAdd = strReturnedAddress.toString();
                        Log.w("MyCurrentloctionaddress", strReturnedAddress.toString());


                    } catch (Exception e) {


                        e.printStackTrace();
                    }
                } else {

                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        });
    }

    @Override
    public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {

        try {
            boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json));
            if (!success) {
                Log.i("Map:Style", "Style parsing failed.");
            } else {
                Log.i("Map:Style", "Style Applied.");
            }
        } catch (Resources.NotFoundException e) {
            Log.i("Map:Style", "Can't find style. Error: ");
        }

        mMap = googleMap;

        mMap.getUiSettings().setCompassEnabled(false);
        mMap.setBuildingsEnabled(true);
        mMap.setOnCameraMoveListener(this);
        mMap.setOnCameraIdleListener(this);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setTiltGesturesEnabled(false);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);


    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        mLastLocation = location;
    }

    @Override
    public void onCameraIdle() {
        try {
            CameraPosition cameraPosition = mMap.getCameraPosition();
            /*srcLat = cameraPosition.target.latitude;
            srcLng = cameraPosition.target.longitude;

            //Intialize animation line
            initializeAvd();
            getAddress(srcLat, srcLng);*/
//            getAddress(context, srcLat, srcLng);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCameraMove() {

        //textAddress.setText(this.getResources().getString(R.string.getting_address));
    }

    @Override
    public void onSuccessAddress(com.example.urban_food.model.Address response) {
        if(isEdit){
            Toast.makeText(activity, "Successfully edited", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(activity, "Added Sucessfully", Toast.LENGTH_SHORT).show();
        }
        onBackPressed();
    }

    @Override
    public void onError(String msg) {

    }


    @Override
    public void ShowProgress() {

    }

    @Override
    public void dismissProgress() {

    }
}