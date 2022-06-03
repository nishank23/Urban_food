package com.example.urban_food.Activities.ManageAddress;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.model.AddFavoriteResponse;
import com.example.urban_food.model.Address;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteAddressPresenter {
    AddressInterface addressInterface;

    public DeleteAddressPresenter(AddressInterface addressInterface) {
        this.addressInterface = addressInterface;
    }

    void deleteAddress(String Addressid){
        ApiClient.getRetrofit().deleteAddress(Addressid).enqueue(new Callback<AddFavoriteResponse>() {
            @Override
            public void onResponse(Call<AddFavoriteResponse> call, Response<AddFavoriteResponse> response) {
                if(response.isSuccessful() &&response!=null){
                    addressInterface.onDelete(response.body().getMessage());
                }else{
                    Common.showSomethingWentWrong();
                }
            }

            @Override
            public void onFailure(Call<AddFavoriteResponse> call, Throwable t) {

                Common.showSomethingWentWrong();
            }
        });
    }


    public void getAddress(){
        ApiClient.getRetrofit().getAddress().enqueue(new Callback<List<Address>>() {
            @Override
            public void onResponse(Call<List<Address>> call, Response<List<Address>> response) {
                if(response.isSuccessful() && response.body()!=null){
                    addressInterface.ongetAddress(response.body());
                }else{
                    Common.showSomethingWentWrong();
                }
            }

            @Override
            public void onFailure(Call<List<Address>> call, Throwable t) {
                Common.showSomethingWentWrong();
            }
        });
    }
}
