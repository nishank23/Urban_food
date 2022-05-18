package com.example.urban_food.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urban_food.databinding.RecyclerItemlistOrderHistoryBinding;
import com.example.urban_food.model.Item;

import java.util.List;

public class PastOrderItemAdapter extends RecyclerView.Adapter<PastOrderItemAdapter.MyHolder> {
    Activity context;
    List<Item> itemList;

    public PastOrderItemAdapter(Activity context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(RecyclerItemlistOrderHistoryBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        if(itemList.get(position).getProduct()!=null && itemList.get(position).getProduct().getName()!=null && itemList.get(position).getQuantity()!=null )
        holder.binding.tvItemlist.setText(itemList.get(position).getQuantity()+"×"+itemList.get(position).getProduct().getName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        RecyclerItemlistOrderHistoryBinding binding;
        public MyHolder(RecyclerItemlistOrderHistoryBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
