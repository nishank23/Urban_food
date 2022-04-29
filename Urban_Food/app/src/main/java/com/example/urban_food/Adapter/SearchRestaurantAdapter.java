package com.example.urban_food.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urban_food.R;
import com.example.urban_food.databinding.PopularWeekRecyclerLayoutBinding;
import com.example.urban_food.model.Shop;

import java.util.List;

public class SearchRestaurantAdapter extends RecyclerView.Adapter<SearchRestaurantAdapter.MyHolder> {
    Activity context;
    List<Shop> list;

    public SearchRestaurantAdapter(Activity context, List<Shop> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(PopularWeekRecyclerLayoutBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.binding.tvPopularRestaurantName.setText(list.get(position).getName());
            holder.binding.tvPopularRestaurantAddress.setText(list.get(position).getAddress());
        Glide
                .with(context)
                .load(list.get(position).getPhoto())
                .into(holder.binding.ivImage);
        if(list.get(position).getPhoto().isEmpty()){
            Glide
                    .with(context)
                    .load(R.mipmap.ic_launcher)
                    .into(holder.binding.ivImage);
        }
        holder.binding.tvRatingDigitPopular.setText(String.valueOf(list.get(position).getRating()));
        holder.binding.tvRatingCountPopular.setText("("+String.valueOf(list.get(position).getRatingStatus())+" Rating)");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        PopularWeekRecyclerLayoutBinding binding;


        public MyHolder(PopularWeekRecyclerLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
