package com.example.urban_food.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urban_food.Modal.ShopsDetailsModal.ProductsItem;
import com.example.urban_food.Modal.ShopsDetailsModal.ShopDetailsCategoriesItem;
import com.example.urban_food.Modal.ShopsDetailsModal.ShopDetailsResponse;
import com.example.urban_food.databinding.MenuRecyclerRecyclerviewBinding;

import java.util.List;

public class RvMenuAdapter extends RecyclerView.Adapter<RvMenuAdapter.Holder> {
    Activity activity;
    List<ProductsItem> productList;

    public RvMenuAdapter(Activity activity, List<ProductsItem> productList) {
        this.activity = activity;
        this.productList = productList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(MenuRecyclerRecyclerviewBinding.inflate(LayoutInflater.from(activity),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
//        holder.binding.tvPrice.setText(productList.get(position).getPrices().getPrice());
        holder.binding.tvItemNameMenu.setText(productList.get(position).getName());
        Glide
                .with(activity)
                .load(productList.get(position).getImages().get(0).getUrl())
                .into(holder.binding.ivItem);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        MenuRecyclerRecyclerviewBinding binding;
        public Holder(MenuRecyclerRecyclerviewBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
