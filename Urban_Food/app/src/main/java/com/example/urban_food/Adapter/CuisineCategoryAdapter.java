package com.example.urban_food.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urban_food.Modal.CuisineModal.Cuisine;
import com.example.urban_food.databinding.TopCategoryRecyclerLayoutBinding;
import com.example.urban_food.fragment.explore.CuisineInterface;

import java.util.List;

public class CuisineCategoryAdapter extends RecyclerView.Adapter<CuisineCategoryAdapter.MyHolder> {
Activity activity;
List<Cuisine> cuisines;
CuisineInterface callback;


    public CuisineCategoryAdapter(Activity activity, List<Cuisine> cuisines,CuisineInterface callback) {
        this.activity = activity;
        this.cuisines = cuisines;
        this.callback=callback;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(TopCategoryRecyclerLayoutBinding.inflate(LayoutInflater.from(activity),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Glide
                .with(activity)
                .load(cuisines.get(position).getUrl())
                .into(holder.binding.ivImage);

        holder.binding.tvProductName.setText(cuisines.get(position).getName());
        holder.itemView.setOnClickListener(view -> {
            callback.cuisineItem(String.valueOf(cuisines.get(position).getId()));
        });

    }

    @Override
    public int getItemCount() {
        return cuisines.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TopCategoryRecyclerLayoutBinding binding;
        public MyHolder(TopCategoryRecyclerLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
