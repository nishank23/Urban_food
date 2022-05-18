package com.example.urban_food.Activities.ForgetPasswordScreen;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.model.ResetPassword;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordPresenter {
    ForgetpasswordView view;
    public  ForgetPasswordPresenter(ForgetpasswordView view){this.view=view;}

    public void resetPassword(HashMap<String,String> map){
        ApiClient.getRetrofit().resetPassword(map).enqueue(new Callback<ResetPassword>() {
            @Override
            public void onResponse(Call<ResetPassword> call, Response<ResetPassword> response) {
                if(response.isSuccessful()&&response.body()!=null){
                view.getSuccess(response.message());
                }else{
                    Common.showToast("Something wrong");
                }
            }

            @Override
            public void onFailure(Call<ResetPassword> call, Throwable t) {
                Common.showToast("Something Wrong in failure"+t.getLocalizedMessage());
            }
        });
    }


}
