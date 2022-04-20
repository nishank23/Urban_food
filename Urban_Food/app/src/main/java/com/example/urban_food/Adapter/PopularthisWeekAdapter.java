package com.example.urban_food.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urban_food.Modal.ExploreModal.ShopsItem;
import com.example.urban_food.databinding.PopularWeekRecyclerLayoutBinding;
import com.example.urban_food.fragment.explore.ExploreInterface;

import java.util.List;

public class PopularthisWeekAdapter extends RecyclerView.Adapter<PopularthisWeekAdapter.MyHolder> {
    Activity activity;
    List<ShopsItem> shopsList;
    ExploreInterface callback;

    public PopularthisWeekAdapter(Activity activity, List<ShopsItem> shopsList,ExploreInterface callback) {
        this.activity = activity;
        this.shopsList = shopsList;
        this.callback=callback;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(PopularWeekRecyclerLayoutBinding.inflate(LayoutInflater.from(activity),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Glide
                .with(activity)
                .load(shopsList.get(position).getPhoto())
                .into(holder.binding.ivImage);
        holder.binding.tvPopularRestaurantName.setText(shopsList.get(position).getName());
        holder.binding.tvPopularRestaurantAddress.setText(shopsList.get(position).getAddress());
        holder.binding.tvRatingDigitPopular.setText(String.valueOf(shopsList.get(position).getRating()));
        holder.binding.tvRatingCountPopular.setText("("+String.valueOf(shopsList.get(position).getRatingStatus())+" Rating)");
        holder.itemView.setOnClickListener(view -> {
            callback.cuisineItem(String.valueOf(shopsList.get(position).getId()),shopsList.get(position).getPhoto());
        });
    }

    @Override
    public int getItemCount() {
        return shopsList.size()-1;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        PopularWeekRecyclerLayoutBinding binding;
        public MyHolder(PopularWeekRecyclerLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
