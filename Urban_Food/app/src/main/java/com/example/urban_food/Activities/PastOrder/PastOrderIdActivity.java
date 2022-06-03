package com.example.urban_food.Activities.PastOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.urban_food.Adapter.PastOrderItemAdapter;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityPastOrderBinding;
import com.example.urban_food.databinding.ActivityPastOrderIdBinding;

import com.example.urban_food.R;

public class PastOrderIdActivity extends AppCompatActivity {
ActivityPastOrderIdBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPastOrderIdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /*GlobalData.orders ;*/
        binding.tvRestaurantName.setText(GlobalData.orders.getShop().getName());
        binding.tvAddress1.setText(GlobalData.orders.getAddress().getMapAddress());
        binding.tvRestaurantAddress.setText(GlobalData.orders.getShop().getAddress());

        binding.ivBackMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if(GlobalData.orders.getStatus().equalsIgnoreCase("CANCELLED")) {
            binding.tvOrderStatus.setText(GlobalData.orders.getStatus());
            binding.ivDenied.setImageResource(R.drawable.ic_danger_svgrepo_com);
            binding.tvShwPaid.setVisibility(View.GONE);
        } else{
            binding.tvOrderStatus.setText(GlobalData.orders.getStatus());
            binding.ivDenied.setImageResource(R.drawable.ic_order_confirmed);
            binding.tvShwPaid.setVisibility(View.VISIBLE);

        }
        Double total = GlobalData.orders.getInvoice().getGross();
        float gst_cal = (float) ((total * GlobalData.orders.getInvoice().getTax()) / 100);
        float commission = (float) ((total * GlobalData.orders.getInvoice().getGrabitComission()) / 100);
        float commisionTax = (float) ((commission * GlobalData.orders.getInvoice().getGrabitComissionTax()) / 100);

        float taxfee = gst_cal + commission + commisionTax;
        float taxfeeround = Math.round(taxfee * 100) / 100;

        binding.tvShwItemtotal.setText("₹ " + String.valueOf(GlobalData.orders.getInvoice().getGross()));
        binding.tvShwtotal.setText("₹ " + String.valueOf(GlobalData.orders.getInvoice().getNet()));
        binding.tvShwFee.setText("₹ " + taxfeeround);
        binding.tvShwDelivery.setText("₹ " + String.valueOf(GlobalData.orders.getInvoice().getDeliveryCharge()));

        if(GlobalData.orders.getInvoice().getPaymentMode().equalsIgnoreCase("cash")){
            binding.tvShwPaid.setText("Cash");
        }
        else{
            binding.tvShwPaid.setText("Paytm");

        }




        PastOrderItemAdapter adapter = new PastOrderItemAdapter(this, GlobalData.orders.getItems());
        binding.rvTimes.setAdapter(adapter);
        binding.rvTimes.setLayoutManager(new LinearLayoutManager(this));





    }
}