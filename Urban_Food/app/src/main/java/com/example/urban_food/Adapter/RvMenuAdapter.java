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
            holder.binding.tvPrice.setText(String.valueOf(productList.get(position).getPrices().getPrice()));
            holder.binding.tvItemNameMenu.setText(productList.get(position).getName());
            Glide
                    .with(activity)
                    .load(productList.get(position).getImages().get(0).getUrl())
                    .into(holder.binding.ivItem);

            holder.binding.btnAdd.setOnClickListener(view -> {
                holder.binding.btnAdd.setVisibility(View.GONE);
                holder.binding.btnMinus.setVisibility(View.VISIBLE);
                holder.binding.btnPlus.setVisibility(View.VISIBLE);
                holder.binding.etQuantity.setVisibility(View.VISIBLE);
            });

            //order quantity increse
            holder.binding.btnPlus.setOnClickListener(view -> {
                int qty=Integer.parseInt(holder.binding.etQuantity.getText().toString());
                qty++;
                holder.binding.etQuantity.setText(String.valueOf(qty));
            });
        //order quantity decrese
        holder.binding.btnMinus.setOnClickListener(view -> {
                int qty=Integer.parseInt(holder.binding.etQuantity.getText().toString());
                if(qty==1){
                    holder.binding.btnAdd.setVisibility(View.VISIBLE);

                    holder.binding.btnMinus.setVisibility(View.GONE);
                    holder.binding.btnPlus.setVisibility(View.GONE);
                    holder.binding.etQuantity.setVisibility(View.GONE);
                }else{
                    qty--;
                    holder.binding.etQuantity.setText(String.valueOf(qty));

                }

            });
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
