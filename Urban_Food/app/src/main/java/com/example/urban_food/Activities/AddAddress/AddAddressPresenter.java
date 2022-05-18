package com.example.urban_food.Activities.AddAddress;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.model.AddFavoriteResponse;
import com.example.urban_food.model.Address;
import com.google.android.gms.common.api.Api;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAddressPresenter {
    AddAddressView view;

    public AddAddressPresenter(AddAddressView view) {
        this.view = view;
    }

    void addAddress(Address address){
        ApiClient.getRetrofit().addAddress(address).enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address>call, Response<Address> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    view.onSuccessAddress(response.body());
                }else{
                    Common.showToast(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                Common.showToast(""+t.getMessage());
            }
        });
    }


    void updateAddress(int id,Address address){
        ApiClient.getRetrofit().updateAddress(id,address).enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                if(response.body()!=null&response.isSuccessful()){
                    view.onSuccessAddress(response.body());
                }else{
                    Common.showToast(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                Common.showToast(""+t.getMessage());
            }
        });
    }

}
