package com.example.urban_food.Api;

import com.example.urban_food.Modal.CuisineModal.Cuisine;
import com.example.urban_food.Modal.LoginModal.LoginResponse;
import com.example.urban_food.Modal.OtpModal.OtpResponse;
import com.example.urban_food.Modal.ProfileModal.ProfileResponse;
import com.example.urban_food.Modal.RegisterModal.RegistrationResponse;
import com.example.urban_food.Modal.ExploreModal.ShopResponse;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {
/*
    @GET("Common/get_property_masters")*//*
    Call<PropertmasterResponse> getPropertyMaster(@Query("user_id")String user_id);*//*

    @GET("Common/get_states")*//*
    Call<StateResponse> getState();
*//*
    @FormUrlEncoded
    @POST("Common/get_cities")
  *//*  Call<CityResponse> getCity(@Field("state_id")String state_id);
*/
    @FormUrlEncoded
    @POST("api/user/otp")
    Call<OtpResponse> getingOtp(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("api/user/register")
    Call<RegistrationResponse> registration(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("oauth/token")
    Call<LoginResponse> Login(@FieldMap HashMap<String,String> map);

    @GET("api/user/profile")
    Call<ProfileResponse> getProfile(@QueryMap HashMap<String, String> map);


    @GET("api/user/shops")
    Call<ShopResponse> getShops(@Query("user_id") String user_id, @Query("latitude") double latitude, @Query("longitude") double longitude);

    @GET("api/user/cuisines")
    Call<List<Cuisine>> getCuisineCategories();
}