package com.example.urban_food.Activities.OngoingOrder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.urban_food.Activities.Home.HomeNewActivity;
import com.example.urban_food.Activities.PastOrder.OrderPresenter;
import com.example.urban_food.Activities.PastOrder.OrderView;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityOngoingOrderBinding;
import com.example.urban_food.databinding.DialogReasonBinding;
import com.example.urban_food.model.Order;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OngoingOrderActivity extends AppCompatActivity implements OrderView, OnMapReadyCallback {
    ActivityOngoingOrderBinding binding;
    boolean shouldStopLoop = false;
    OrderPresenter orderPresenter;
    Dialog dialog;
    Handler mHandler;
    Runnable runnable;
    private GoogleMap mMap;
    double lat,lon,deslat,deslong;
    MarkerOptions origin, destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOngoingOrderBinding.inflate(getLayoutInflater());
        orderPresenter = new OrderPresenter(this);
        setContentView(binding.getRoot());


        mHandler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                orderPresenter.getOngoingOrder();

                if (!shouldStopLoop) {

                    //call api here

                    mHandler.postDelayed(this, 10000);
                }
            }
        };

        mHandler.post(runnable);


    }


    void setSpinnerAdapter(int id) {

        dialog = new Dialog(this);
        DialogReasonBinding dialogReasonBinding = DialogReasonBinding.inflate(getLayoutInflater());
        dialog.setContentView(dialogReasonBinding.getRoot());
        dialogReasonBinding.tvOrdertitle.setText("ORDER");
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ArrayList<String> reason = new ArrayList<>();
        reason.add("I want to cancel my order");
        reason.add("I dont want to order any more");
        reason.add("Right your own reason for cancel");


        ArrayAdapter<String> areaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reason);
        dialogReasonBinding.spnreason.setAdapter(areaAdapter);

        dialogReasonBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderPresenter.deleteOrder(String.valueOf(id), dialogReasonBinding.spnreason.getSelectedItem().toString());
            }
        });
        dialogReasonBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }


    @Override
    public void getOrder(List<Order> orderList) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void getOrderId(int id) {


    }

    @Override
    public void getOrderIdSuccess(Order orderList) {
    }

    @Override
    public void getOngoingOrder(List<Order> orderList) {
         /*lat =GlobalData.userAddressSelect.getLatitude();
         lon= GlobalData.userAddressSelect.getLongitude();

*/




        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

         deslat=orderList.get(0).getShop().getLatitude();
         deslong=orderList.get(0).getShop().getLongitude();
        origin = new MarkerOptions().position(new LatLng(GlobalData.userAddressSelect.getLatitude(), GlobalData.userAddressSelect.getLongitude())).title("User").snippet("origin");
        destination = new MarkerOptions().position(new LatLng(deslat,  deslong)).title("Restaurant").snippet("destination");

        String url = getDirectionsUrl(origin.getPosition(), destination.getPosition());

        DownloadTask downloadTask = new DownloadTask();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);

        if (orderList != null) {
            for (int i = 0; i < orderList.get(0).getOrdertiming().size(); i++) {


                if (orderList.get(0).getOrdertiming().get(i).getStatus().contains("ORDERED")) {
                    binding.ivOrderPlaced.setAlpha(1.00F);
                    binding.tvOrderPlaced.setTextColor(ContextCompat.getColor(this, R.color.black));
                    binding.tvOrderPlaced2.setTextColor(ContextCompat.getColor(this, R.color.grey));
                    binding.placedot.setAlpha(1.00F);
                }
                if (orderList.get(0).getOrdertiming().get(i).getStatus().contains("RECEIVED")) {
                    binding.ivOrderConfirmed.setAlpha(1.00F);
                    binding.tvOrderConfirmed.setTextColor(ContextCompat.getColor(this, R.color.black));
                    binding.tvOrderConfirmed2.setTextColor(ContextCompat.getColor(this, R.color.grey));
                    binding.confirmdot.setAlpha(1.00F);
                }
                if (orderList.get(0).getOrdertiming().get(i).getStatus().contains("SEARCHING")) {
                    binding.ivOrderProcessed.setAlpha(1.00F);
                    binding.tvOrderProcessed.setTextColor(ContextCompat.getColor(this, R.color.black));
                    binding.tvOrderProcessed2.setTextColor(ContextCompat.getColor(this, R.color.grey));
                    binding.processeddot.setAlpha(1.00F);
                }
                if (orderList.get(0).getOrdertiming().get(i).getStatus().contains("PICKED")) {
                    binding.ivOrderPicked.setAlpha(1.00F);
                    binding.tvOrderPicked.setTextColor(ContextCompat.getColor(this, R.color.black));
                    binding.tvOrderPicked2.setTextColor(ContextCompat.getColor(this, R.color.grey));
                    binding.pickeddot.setAlpha(1.00F);
                }
                if (orderList.get(0).getOrdertiming().get(i).getStatus().contains("PICKED")) {
                    binding.ivOrderDelivered.setAlpha(1.00F);
                    binding.tvOrderDelivered.setTextColor(ContextCompat.getColor(this, R.color.black));
                    binding.tvOrderDelivered2.setTextColor(ContextCompat.getColor(this, R.color.grey));

                }

                binding.tvTitleCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setSpinnerAdapter(orderList.get(0).getId());
                    }
                });

                Log.d("checkingorder", "" + orderList.toString());
            }


        }
    }

    @Override
    public void deleteOrder(String msg) {
        shouldStopLoop = true;
        Toast.makeText(this, "Order Delected Succesfullty", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, HomeNewActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(runnable);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(origin);
        mMap.addMarker(destination);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin.getPosition(), 14));
    }

    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            String data = "";

            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ParserTask parserTask = new ParserTask();
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DataParser parser = new DataParser();

                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList points = new ArrayList();
            PolylineOptions lineOptions = new PolylineOptions();

            for (int i = 0; i < result.size(); i++) {

                List<HashMap<String, String>> path = result.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                lineOptions.addAll(points);
                lineOptions.width(12);
                lineOptions.color(Color.RED);
                lineOptions.geodesic(true);

            }

            // Drawing polyline in the Google Map
            if (points.size() != 0)
                mMap.addPolyline(lineOptions);
        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String sensor = "sensor=false";

        //setting transportation mode
        String mode = "mode=driving";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + "AIzaSyClH2UrK-86wwXvFTKLX2nHY7K9Ne9qLs0";

        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

}


