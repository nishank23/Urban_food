package com.example.urban_food.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urban_food.databinding.DiscoverNewplaceRecyclerlayoutBinding;
import com.example.urban_food.fragment.explore.ExploreInterface;
import com.example.urban_food.model.Shop;

import java.util.List;

public class DiscoverNewPlacesAdapter extends RecyclerView.Adapter<DiscoverNewPlacesAdapter.MyHolder> {

    Activity activity;
    List<Shop> shopsList;
    ExploreInterface CallBack;


    public DiscoverNewPlacesAdapter(Activity activity, List<Shop> shopsList, ExploreInterface callBack) {
        this.activity = activity;
        this.shopsList = shopsList;
        CallBack = callBack;
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
      holder.binding.tvRatingDigit.setText(String.valueOf(shopsList.get(position).getRating()));
      holder.binding.tvRatingCount.setText("("+String.valueOf(shopsList.get(position).getRatingStatus())+" Rating)");

      holder.itemView.setOnClickListener(view -> {
          CallBack.cuisineItem(String.valueOf(shopsList.get(position).getId()),shopsList.get(position).getPhoto());
          Log.d("shopid",""+shopsList.get(position).getId());
      });
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
