package com.example.urban_food.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urban_food.databinding.PopularWeekRecyclerLayoutBinding;
import com.example.urban_food.fragment.explore.ExploreInterface;
import com.example.urban_food.model.Available;
import com.example.urban_food.model.FavoriteList;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyHolder> {

    Activity context;
    List<Available> list;
    ExploreInterface callback;

    public FavoriteAdapter(Activity context, List<Available> list, ExploreInterface callback) {
        this.context = context;
        this.list = list;
        this.callback = callback;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(PopularWeekRecyclerLayoutBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Glide
                .with(context)
                .load(list.get(position).getShop().getPhoto())
                .into(holder.binding.ivImage);
        holder.binding.tvPopularRestaurantName.setText(list.get(position).getShop().getName());
        holder.binding.tvPopularRestaurantAddress.setText(list.get(position).getShop().getAddress());
        holder.binding.tvRatingDigitPopular.setText(String.valueOf(list.get(position).getShop().getRating()));
        holder.binding.tvRatingCountPopular.setText("("+String.valueOf(list.get(position).getShop().getRatingStatus())+" Rating)");


        holder.itemView.setOnClickListener(view -> {
            callback.cuisineItem(String.valueOf(list.get(position).getShopId()),list.get(position).getShop().getPhoto());
        });


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
