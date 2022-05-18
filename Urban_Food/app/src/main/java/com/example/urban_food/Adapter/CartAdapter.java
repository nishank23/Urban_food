package com.example.urban_food.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urban_food.Activities.ShopsDetail.cart.RvMenuInterface;
import com.example.urban_food.databinding.RecyclerLayoutItemsMyOrderBinding;
import com.example.urban_food.model.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Holder> {
    Activity activity;
    List<Cart> list;
    RvMenuInterface callback;

    public CartAdapter(Activity activity, List<Cart> list, RvMenuInterface callback) {
        this.activity = activity;
        this.list = list;
        this.callback=callback;
    }
    @NonNull
    @Override
    public CartAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(RecyclerLayoutItemsMyOrderBinding.inflate(LayoutInflater.from(activity),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.Holder holder, int position) {
        holder.binding.tvItemName.setText(list.get(position).getProduct().getName());
        holder.binding.etQuantity.setText(String.valueOf(list.get(position).getQuantity()));

        holder.binding.btnPlus.setOnClickListener(view -> {
            int qty = Integer.parseInt(holder.binding.etQuantity.getText().toString());
            qty++;
            holder.binding.etQuantity.setText(String.valueOf(qty));
            callback.cartParaWithCardId(list.get(position).getProductId(), qty, String.valueOf(list.get(position).getId()));
        });
        //order quantity decrese
        holder.binding.btnMinus.setOnClickListener(view -> {
            int qty = Integer.parseInt(holder.binding.etQuantity.getText().toString());
            qty--;

            if (qty == 0) {
                callback.cartParaWithCardId(list.get(position).getProductId(), 0, String.valueOf(list.get(position).getId()));
                list.remove(position);
                notifyDataSetChanged();
            } else {
                holder.binding.etQuantity.setText(String.valueOf(qty));
                callback.cartParaWithCardId(list.get(position).getProductId(), qty, String.valueOf(list.get(position).getId()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class
    Holder extends RecyclerView.ViewHolder {
        RecyclerLayoutItemsMyOrderBinding binding;
        public Holder(RecyclerLayoutItemsMyOrderBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
