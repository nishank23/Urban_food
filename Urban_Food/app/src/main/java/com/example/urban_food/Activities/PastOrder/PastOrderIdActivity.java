package com.example.urban_food.Activities.PastOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.urban_food.Adapter.PastOrderItemAdapter;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityPastOrderBinding;
import com.example.urban_food.databinding.ActivityPastOrderIdBinding;

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



        if(GlobalData.orders.getStatus().equalsIgnoreCase("CANCELLED")) {
            binding.tvOrderStatus.setText(GlobalData.orders.getStatus());
            binding.ivDenied.setImageResource(R.drawable.ic_danger_svgrepo_com);
        } else{
            binding.tvOrderStatus.setText(GlobalData.orders.getStatus());
            binding.ivDenied.setImageResource(R.drawable.ic_order_confirmed);
        }


        PastOrderItemAdapter adapter = new PastOrderItemAdapter(this, GlobalData.orders.getItems());
        binding.rvTimes.setAdapter(adapter);
        binding.rvTimes.setLayoutManager(new LinearLayoutManager(this));





    }
}