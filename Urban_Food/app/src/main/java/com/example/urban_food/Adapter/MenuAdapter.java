package com.example.urban_food.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urban_food.Activities.ShopsDetail.cart.RvMenuInterface;
import com.example.urban_food.databinding.RecyclerLayoutMenuBinding;
import com.example.urban_food.model.Category;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyHolder> {

    Activity activity;
    List<Category> shopDetailList;
    RvMenuInterface callback;

    public MenuAdapter(Activity activity, List<Category> shopDetailList, RvMenuInterface callback) {
        this.activity = activity;
        this.shopDetailList = shopDetailList;
        this.callback=callback;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(RecyclerLayoutMenuBinding.inflate(LayoutInflater.from(activity),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.binding.tvItemCategory.setText(shopDetailList.get(position).getName());
        int childAdapterSize=shopDetailList.get(position).getProducts().size();

        if(childAdapterSize != 0){
            RvMenuAdapter rvMenuAdapter = new RvMenuAdapter(activity, shopDetailList.get(holder.getAdapterPosition()).getProducts(), new RvMenuInterface() {
                @Override
                public void cartParaWithCardId(int id, int value, String CartValue) {
                    callback.cartParaWithCardId(id,value,CartValue);
                }

                @Override
                public void cartPara(int id, int value) {
                    callback.cartPara(id,value);
                }

                @Override
                public void clearCartPara(Boolean check, int id, int value) {
                    callback.clearCartPara(check,id,value);
                }

            });
            holder.binding.rvMenu.setAdapter(rvMenuAdapter);
            holder.binding.rvMenu.setLayoutManager(new LinearLayoutManager(activity));
        }else{
            holder.binding.tvItemCategory.setVisibility(View.GONE);
        }
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
