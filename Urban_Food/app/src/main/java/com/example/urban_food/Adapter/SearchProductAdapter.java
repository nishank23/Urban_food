package com.example.urban_food.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.Modal.SearchModal.ProductsItem;
import com.example.urban_food.Modal.SearchModal.SearchResponse;
import com.example.urban_food.Modal.SearchModal.ShopsItem;
import com.example.urban_food.R;
import com.example.urban_food.databinding.PopularWeekRecyclerLayoutBinding;
import com.example.urban_food.databinding.SearchProductRecylcerLayoutBinding;

import java.util.List;

public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.MyHolder> {
    Activity context;
    List<ProductsItem> productItemList;

    public SearchProductAdapter(Activity context, List<ProductsItem> productItemList) {
        this.context = context;
        this.productItemList = productItemList;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(SearchProductRecylcerLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {


        if(productItemList.get(position).getFoodType().contains("non-veg")){
            holder.binding.ivImage.setColorFilter(ContextCompat.getColor(context,R.color.red));
        }
        holder.binding.tvRestaurantName.setText(productItemList.get(position).getShop().getName());
        holder.binding.tvProductName.setText(productItemList.get(position).getName());


    }


    @Override
    public int getItemCount() {
        return productItemList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        SearchProductRecylcerLayoutBinding binding;

        public MyHolder(SearchProductRecylcerLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
