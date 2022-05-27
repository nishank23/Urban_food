package com.example.urban_food.Activities.PastOrder;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.urban_food.Adapter.PastOrderAdapter;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityPastOrderBinding;
import com.example.urban_food.databinding.LayoutLoadingBinding;
import com.example.urban_food.databinding.LayoutOrderloadingBinding;
import com.example.urban_food.model.Order;

import java.util.List;

public class PastOrder extends AppCompatActivity implements OrderView {
com.example.urban_food.databinding.ActivityPastOrderBinding binding;
PastOrderAdapter pastOrderAdapter;
OrderPresenter orderPresenter;
Dialog dialog;
LayoutOrderloadingBinding layoutLoadingBinding;
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
        pastOrderAdapter = new PastOrderAdapter(this,orderList,this );
        binding.myOrderlist.setAdapter(pastOrderAdapter);
        binding.myOrderlist.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showProgress() {
        layoutLoadingBinding = LayoutOrderloadingBinding.inflate(LayoutInflater.from(this));
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layoutLoadingBinding.getRoot());
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
       /* wmlp.gravity = Gravity.BOTTOM | Gravity.CENTER;
        wmlp.y = 250;
       */ dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.setCanceledOnTouchOutside(false);

        wmlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;

        dialog.getWindow().setAttributes(wmlp);

        dialog.show();

        binding.clActionBar.setVisibility(View.GONE);
        binding.myOrderlist.setVisibility(View.GONE);

    }

    @Override
    public void dismissProgress() {
        dialog.dismiss();
        binding.clActionBar.setVisibility(View.VISIBLE);
        binding.myOrderlist.setVisibility(View.VISIBLE);


    }

    @Override
    public void getOrderId(int id) {
        orderPresenter.getOrderId(id);

    }

    @Override
    public void getOrderIdSuccess(Order orders) {
        GlobalData.orders =orders;
        startActivity(new Intent(this,PastOrderIdActivity.class));
        finish();

    }

    @Override
    public void getOngoingOrder(List<Order> orderList) {

    }

    @Override
    public void deleteOrder(String msg) {

    }
}