package com.example.urban_food.Activities.OngoingOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.urban_food.Activities.Home.HomeActivity;
import com.example.urban_food.Activities.Home.HomeNewActivity;
import com.example.urban_food.Activities.PastOrder.OrderPresenter;
import com.example.urban_food.Activities.PastOrder.OrderView;
import com.example.urban_food.Activities.PastOrder.PastOrder;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityOngoingOrderBinding;
import com.example.urban_food.databinding.DialogReasonBinding;
import com.example.urban_food.fragment.explore.Explore;
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
    Dialog dialog;
    Handler mHandler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOngoingOrderBinding.inflate(getLayoutInflater());
        orderPresenter = new OrderPresenter(this);
        setContentView(binding.getRoot());


        mHandler = new Handler();

        runnable = new Runnable() {
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


    void setSpinnerAdapter(int id) {

        dialog = new Dialog(this);
        DialogReasonBinding dialogReasonBinding = DialogReasonBinding.inflate(getLayoutInflater());
        dialog.setContentView(dialogReasonBinding.getRoot());
        dialogReasonBinding.tvOrdertitle.setText("ORDER");
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ArrayList<String> reason = new ArrayList<>();
        reason.add("I want to cancel my order");
        reason.add("I dont want to order any more");
        reason.add("Right your own reason for cancel");


        ArrayAdapter<String> areaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reason);
        dialogReasonBinding.spnreason.setAdapter(areaAdapter);

        dialogReasonBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderPresenter.deleteOrder(String.valueOf(id), dialogReasonBinding.spnreason.getSelectedItem().toString());
            }
        });
        dialogReasonBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

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
        if (orderList != null) {
            for (int i = 0; i < orderList.get(0).getOrdertiming().size(); i++) {


                if (orderList.get(0).getOrdertiming().get(i).getStatus().contains("ORDERED")) {
                    binding.ivOrderPlaced.setAlpha(1.00F);
                    binding.tvOrderPlaced.setTextColor(ContextCompat.getColor(this, R.color.black));
                    binding.tvOrderPlaced2.setTextColor(ContextCompat.getColor(this, R.color.grey));
                    binding.placedot.setAlpha(1.00F);
                }
                if (orderList.get(0).getOrdertiming().get(i).getStatus().contains("RECEIVED")) {
                    binding.ivOrderConfirmed.setAlpha(1.00F);
                    binding.tvOrderConfirmed.setTextColor(ContextCompat.getColor(this, R.color.black));
                    binding.tvOrderConfirmed2.setTextColor(ContextCompat.getColor(this, R.color.grey));
                    binding.confirmdot.setAlpha(1.00F);
                }
                if (orderList.get(0).getOrdertiming().get(i).getStatus().contains("SEARCHING")) {
                    binding.ivOrderProcessed.setAlpha(1.00F);
                    binding.tvOrderProcessed.setTextColor(ContextCompat.getColor(this, R.color.black));
                    binding.tvOrderProcessed2.setTextColor(ContextCompat.getColor(this, R.color.grey));
                    binding.processeddot.setAlpha(1.00F);
                }
                if (orderList.get(0).getOrdertiming().get(i).getStatus().contains("PICKED")) {
                    binding.ivOrderPicked.setAlpha(1.00F);
                    binding.tvOrderPicked.setTextColor(ContextCompat.getColor(this, R.color.black));
                    binding.tvOrderPicked2.setTextColor(ContextCompat.getColor(this, R.color.grey));
                    binding.pickeddot.setAlpha(1.00F);
                }
                if (orderList.get(0).getOrdertiming().get(i).getStatus().contains("PICKED")) {
                    binding.ivOrderDelivered.setAlpha(1.00F);
                    binding.tvOrderDelivered.setTextColor(ContextCompat.getColor(this, R.color.black));
                    binding.tvOrderDelivered2.setTextColor(ContextCompat.getColor(this, R.color.grey));

                }

                binding.tvTitleCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setSpinnerAdapter(orderList.get(0).getId());
                    }
                });

                Log.d("checkingorder", "" + orderList.toString());
            }


        }
    }

    @Override
    public void deleteOrder(String msg) {
        shouldStopLoop = true;
        Toast.makeText(this, "Order Delected Succesfullty", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, HomeNewActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(runnable);
    }
}