package com.example.urban_food.Activities.PastOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.urban_food.Adapter.PastOrderAdapter;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityPastOrderBinding;
import com.example.urban_food.model.Order;

import java.util.List;

public class PastOrder extends AppCompatActivity implements OrderView {
ActivityPastOrderBinding binding;
PastOrderAdapter pastOrderAdapter;
OrderPresenter orderPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPastOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        orderPresenter =new OrderPresenter(this);
        orderPresenter.getOrder();
    }

    @Override
    public void getOrder(List<Order> orderList) {
        pastOrderAdapter = new PastOrderAdapter(this,orderList);
        binding.myOrderlist.setAdapter(pastOrderAdapter);
        binding.myOrderlist.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }
}