package com.example.urban_food.Activities.PastOrder;

import com.example.urban_food.Api.ApiClient;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderPresenter {
    OrderView view;

    public OrderPresenter(OrderView view) {
        this.view = view;
    }


    public void getOrder(){
        ApiClient.getRetrofit().getOrder().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if(response.isSuccessful() && response.body()!=null){
                    view.getOrder(response.body());
                }else {
                    Common.showToast(response.headers().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

                Common.showToast(""+t.getMessage()+""+t.getLocalizedMessage());
            }
        });
    }
}
