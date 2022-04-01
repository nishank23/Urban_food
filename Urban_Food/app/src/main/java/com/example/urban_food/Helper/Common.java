package com.example.urban_food.Helper;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Common {

    //For Internet Connection Checking
    public static boolean isConnected(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showSomethingWentWrong(Context context) {
        Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
    }

    public static void showNoInternet(Context context) {
        Toast.makeText(context, "Please connect to internet.", Toast.LENGTH_SHORT).show();
    }

}
