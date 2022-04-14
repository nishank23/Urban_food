package com.example.urban_food.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urban_food.Modal.ShopModal.ShopResponse;
import com.example.urban_food.Modal.ShopModal.ShopsItem;
import com.example.urban_food.databinding.DiscoverNewplaceRecyclerlayoutBinding;

import java.util.List;

public class DiscoverNewPlacesAdapter extends RecyclerView.Adapter<DiscoverNewPlacesAdapter.MyHolder> {

    Activity activity;
    List<ShopsItem> shopsList;

    public DiscoverNewPlacesAdapter(Activity activity, List<ShopsItem> shopsList) {
        this.activity = activity;
        this.shopsList = shopsList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(DiscoverNewplaceRecyclerlayoutBinding.inflate(LayoutInflater.from(activity),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Glide
                .with(activity)
                .load(shopsList.get(position).getPhoto())
                .into(holder.binding.ivImage);

      holder.binding.tvRestaurantName.setText(shopsList.get(position).getName());
      holder.binding.tvRestaurantAddress.setText(shopsList.get(position).getAddress());
      holder.binding.tvRatingDigit.setText(shopsList.get(position).getRating());
      holder.binding.tvRatingCount.setText(shopsList.get(position).getRatingStatus());

    }

    @Override
    public int getItemCount() {
        return shopsList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        DiscoverNewplaceRecyclerlayoutBinding binding;

        public MyHolder(DiscoverNewplaceRecyclerlayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
