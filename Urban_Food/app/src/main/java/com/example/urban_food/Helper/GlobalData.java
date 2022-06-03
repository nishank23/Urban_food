package com.example.urban_food.Helper;

import com.example.urban_food.Adapter.AddressAdapter;
import com.example.urban_food.model.Address;
import com.example.urban_food.model.Cart;
import com.example.urban_food.model.Order;
import com.example.urban_food.model.User;

import java.util.List;

public class GlobalData {
    public static List<com.example.urban_food.model.Cart> Cart;
    public static double latitude;
    public static double longitude;
    public static String device_id;
    public static int cart_id;
    public static String fcm_token;
    public static User users;
    public static List<com.example.urban_food.model.Address> Address;
    public static String search;
    public static Address editAddress;
    public static Address userAddressSelect;
    public static Order orders;
    public static double latitudeC;
    public static double longitudeC;
    public static String address ="";
    public static String addressHeader="";
    public static boolean pastorder=false;

}
