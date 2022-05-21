package com.example.urban_food.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urban_food.Activities.PastOrder.OrderView;
import com.example.urban_food.databinding.RecyclerOrderHistoryBinding;
import com.example.urban_food.model.Order;

import java.util.List;

public class PastOrderAdapter extends RecyclerView.Adapter<PastOrderAdapter.MyHolder> {
    Activity context;
    List<Order> orderList;
    OrderView orderView;

    public PastOrderAdapter(Activity context, List<Order> orderList,OrderView orderView) {
        this.context = context;
        this.orderList = orderList;
        this.orderView = orderView;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(RecyclerOrderHistoryBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.binding.tvRestaurantName.setText(orderList.get(position).getShop().getName());
        holder.binding.tvRestaurantAddress.setText(orderList.get(position).getShop().getAddress());
        holder.binding.tvTimes.setText(orderList.get(position).getCreatedAt());
        holder.binding.tvItemAmount.setText(orderList.get(position).getInvoice().getNet().toString());

        PastOrderItemAdapter adapter = new PastOrderItemAdapter(context, orderList.get(position).getItems());
        holder.binding.historyItemRecyclerview.setAdapter(adapter);
        holder.binding.historyItemRecyclerview.setLayoutManager(new LinearLayoutManager(context));
        holder.itemView.setOnClickListener(view -> {
            orderView.getOrderId(orderList.get(position).getId());
        });

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        RecyclerOrderHistoryBinding binding;

        public MyHolder(RecyclerOrderHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
