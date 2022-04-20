package com.example.urban_food.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urban_food.Activites.ShopsDetail.ShopsDetailsActivity;
import com.example.urban_food.Modal.SearchModal.CategoriesItem;
import com.example.urban_food.Modal.ShopsDetailsModal.ShopDetailsCategoriesItem;
import com.example.urban_food.databinding.RecyclerLayoutMenuBinding;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyHolder> {

    Activity activity;
    List<ShopDetailsCategoriesItem> shopDetailList;

    public MenuAdapter(Activity activity, List<ShopDetailsCategoriesItem> shopDetailList) {
        this.activity = activity;
        this.shopDetailList = shopDetailList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(RecyclerLayoutMenuBinding.inflate(LayoutInflater.from(activity),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.binding.tvItemCategory.setText(shopDetailList.get(position).getName());

        RvMenuAdapter rvMenuAdapter = new RvMenuAdapter(activity,shopDetailList.get(holder.getAdapterPosition()).getProducts());
        holder.binding.rvMenu.setAdapter(rvMenuAdapter);
        holder.binding.rvMenu.setLayoutManager(new LinearLayoutManager(activity));
    }

    @Override
    public int getItemCount() {
        return shopDetailList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        RecyclerLayoutMenuBinding binding;

        public MyHolder(RecyclerLayoutMenuBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
