package com.example.urban_food.Activities.OngoingOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.urban_food.Activities.PastOrder.OrderPresenter;
import com.example.urban_food.Activities.PastOrder.OrderView;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityOngoingOrderBinding;
import com.example.urban_food.model.Order;
import com.example.urban_food.model.Ordertiming;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

public class OngoingOrderActivity extends AppCompatActivity implements OrderView {
    ActivityOngoingOrderBinding binding;
    boolean shouldStopLoop = false;
    OrderPresenter orderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOngoingOrderBinding.inflate(getLayoutInflater());
        orderPresenter = new OrderPresenter(this);
        setContentView(binding.getRoot());

        Handler mHandler = new Handler();

        Runnable runnable = new Runnable() {
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
        if(orderList != null){
            for(int i =0;i<orderList.get(0).getOrdertiming().size();i++){


                if(orderList.get(0).getOrdertiming().get(i).getStatus().contains("ORDERED")){
                    binding.ivOrderPlaced.setAlpha(1.00F);
                    binding.tvOrderPlaced.setTextColor(ContextCompat.getColor(this, R.color.black));
                    binding.tvOrderPlaced2.setTextColor(ContextCompat.getColor(this, R.color.grey));
                    binding.placedot.setAlpha(1.00F);
                }
                if(orderList.get(0).getOrdertiming().get(i).getStatus().contains("RECEIVED")) {
                    binding.ivOrderConfirmed.setAlpha(1.00F);
                    binding.tvOrderConfirmed.setTextColor(ContextCompat.getColor(this, R.color.black));
                    binding.tvOrderConfirmed2.setTextColor(ContextCompat.getColor(this, R.color.grey));
                    binding.confirmdot.setAlpha(1.00F);
                }
                if(orderList.get(0).getOrdertiming().get(i).getStatus().contains("SEARCHING")) {
                    binding.ivOrderProcessed.setAlpha(1.00F);
                    binding.tvOrderProcessed.setTextColor(ContextCompat.getColor(this, R.color.black));
                    binding.tvOrderProcessed2.setTextColor(ContextCompat.getColor(this, R.color.grey));
                    binding.processeddot.setAlpha(1.00F);
                    }if(orderList.get(0).getOrdertiming().get(i).getStatus().contains("PICKED")) {
                    binding.ivOrderPicked.setAlpha(1.00F);
                    binding.tvOrderPicked.setTextColor(ContextCompat.getColor(this, R.color.black));
                    binding.tvOrderPicked2.setTextColor(ContextCompat.getColor(this, R.color.grey));
                    binding.pickeddot.setAlpha(1.00F);
                }if(orderList.get(0).getOrdertiming().get(i).getStatus().contains("PICKED")) {
                    binding.ivOrderDelivered.setAlpha(1.00F);
                    binding.tvOrderDelivered.setTextColor(ContextCompat.getColor(this, R.color.black));
                    binding.tvOrderDelivered2.setTextColor(ContextCompat.getColor(this, R.color.grey));

                }


                    Log.d("checkingorder", ""+orderList.toString());
            }



        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Handler mHandler = new Handler();

        Runnable runnable = new Runnable() {
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
}