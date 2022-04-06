package com.example.urban_food.Api;

import com.example.urban_food.Modal.OtpModal.OtpResponse;
import com.example.urban_food.Modal.RegisterModal.RegistrationResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}