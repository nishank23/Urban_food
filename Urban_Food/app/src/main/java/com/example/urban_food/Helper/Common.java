package com.example.urban_food.Helper;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Common {

    //For Internet Connection Checking
    public static boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static final String isLoggedIn = "isLoggedIn";
    public static final String userName = "userName";
    public static final String userToken = "userToken";


    public static void showToast(String msg) {
        Toast.makeText(MyApplication.instance, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showSomethingWentWrong() {
        Toast.makeText(MyApplication.instance, "Something went wrong.", Toast.LENGTH_SHORT).show();
    }

    public static void showNoInternet() {
        Toast.makeText(MyApplication.instance, "Please connect to internet.", Toast.LENGTH_SHORT).show();
    }

}
