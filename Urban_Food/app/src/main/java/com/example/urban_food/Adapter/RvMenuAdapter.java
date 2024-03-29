package com.example.urban_food.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urban_food.Activities.ShopsDetail.cart.RvMenuInterface;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.databinding.MenuRecyclerRecyclerviewBinding;
import com.example.urban_food.model.Product;

import java.util.List;

public class RvMenuAdapter extends RecyclerView.Adapter<RvMenuAdapter.Holder> {
    Activity activity;
    List<Product> productList;
    RvMenuInterface callback;

    public RvMenuAdapter(Activity activity, List<Product> productList, RvMenuInterface callback) {
        this.activity = activity;
        this.productList = productList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(MenuRecyclerRecyclerviewBinding.inflate(LayoutInflater.from(activity), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.binding.tvPrice.setText(String.valueOf(productList.get(position).getPrices().getPrice()));
        holder.binding.tvItemNameMenu.setText(productList.get(position).getName());
        Glide
                .with(activity)
                .load(productList.get(position).getImages().get(0).getUrl())
                .into(holder.binding.ivItem);

/*
        Toast.makeText(activity, GlobalData.users.getId() +" "+productList.get(position).getShopId()+"hello" +holder.getAdapterPosition() + " " + productList.get(position).getCart().size(), Toast.LENGTH_SHORT).show();
*/
        if (productList.get(position).getCart().size() > 0) {
            holder.binding.btnAdd.setVisibility(View.GONE);
            holder.binding.btnMinus.setVisibility(View.VISIBLE);
            holder.binding.btnPlus.setVisibility(View.VISIBLE);
            holder.binding.etQuantity.setVisibility(View.VISIBLE);

            //set quantity from cart
            holder.binding.etQuantity.setText(String.valueOf(productList.get(position).getCart().get(0).getQuantity()));
        }
         else {
            holder.binding.btnAdd.setVisibility(View.VISIBLE);
            holder.binding.btnMinus.setVisibility(View.GONE);
            holder.binding.btnPlus.setVisibility(View.GONE);
            holder.binding.etQuantity.setVisibility(View.GONE);
        }

        holder.binding.btnAdd.setOnClickListener(view -> {

            if(GlobalData.Cart.size() == 0){
                holder.binding.btnAdd.setVisibility(View.GONE);
                holder.binding.btnMinus.setVisibility(View.VISIBLE);
                holder.binding.btnPlus.setVisibility(View.VISIBLE);
                holder.binding.etQuantity.setVisibility(View.VISIBLE);

                if (productList.get(position).getCart().size() == 0) {

                    callback.cartPara(productList.get(position).getId(), 1);
                    holder.binding.etQuantity.setText(String.valueOf(1));
                }
            }else{
                if(GlobalData.Cart.get(0).getProduct().getShopId().equals(productList.get(position).getShopId())){
                    holder.binding.btnAdd.setVisibility(View.GONE);
                    holder.binding.btnMinus.setVisibility(View.VISIBLE);
                    holder.binding.btnPlus.setVisibility(View.VISIBLE);
                    holder.binding.etQuantity.setVisibility(View.VISIBLE);

                    if (productList.get(position).getCart().size() == 0) {
                        callback.cartPara(productList.get(position).getId(), 1);

                    }
                }else {
                    AlertDialog.Builder dialog=new AlertDialog.Builder(activity);
                    dialog.setTitle("Confirmation");
                    dialog.setMessage("you already order from one restaurant either you override order or click on cancel");
                    dialog.setNegativeButton("Cancel",(dialogInterface, i) -> dialogInterface.dismiss());
                    dialog.setPositiveButton("Add",(dialogInterface, i) -> {
                        callback.clearCartPara(true,productList.get(position).getId(),1);

                        Log.d("clear",""+productList.get(position).getId());
                        dialogInterface.dismiss();
                        holder.binding.etQuantity.setText(String.valueOf(1));


                        holder.binding.btnAdd.setVisibility(View.GONE);
                        holder.binding.btnMinus.setVisibility(View.VISIBLE);
                        holder.binding.btnPlus.setVisibility(View.VISIBLE);
                        holder.binding.etQuantity.setVisibility(View.VISIBLE);
                    });
                    dialog.show();
                }
            }

        });

        //order quantity increse
        holder.binding.btnPlus.setOnClickListener(view -> {
            int cartid=0;
            int qty = Integer.parseInt(holder.binding.etQuantity.getText().toString()) +1;
            for(int i =0;i<GlobalData.Cart.size();i++) {
                if (productList.get(position).getId().equals(GlobalData.Cart.get(i).getProductId()))
                {
                    cartid=GlobalData.Cart.get(i).getId();
                }
            }
            callback.cartParaWithCardId(productList.get(position).getId(), qty, /*String.valueOf(productList.get(position).getCart().get(0).getId())*/ String.valueOf(cartid));
            holder.binding.etQuantity.setText(String.valueOf(qty));

        });
        //order quantity decrese
        holder.binding.btnMinus.setOnClickListener(view -> {
            int qty = Integer.parseInt(holder.binding.etQuantity.getText().toString()) - 1 ;
            int cartid=0;
            for(int i =0;i<GlobalData.Cart.size();i++) {
                if (productList.get(position).getId().equals(GlobalData.Cart.get(i).getProductId()) && GlobalData.Cart!=null) {
                    cartid = GlobalData.Cart.get(i).getId();
                }
            }
            if (qty == 0) {

                callback.cartParaWithCardId(productList.get(position).getId(), 0,String.valueOf(cartid));
                holder.binding.btnMinus.setVisibility(View.GONE);
                holder.binding.btnPlus.setVisibility(View.GONE);
                holder.binding.etQuantity.setVisibility(View.GONE);
                holder.binding.btnAdd.setVisibility(View.VISIBLE);
            } else {
                holder.binding.etQuantity.setText(String.valueOf(qty));

                callback.cartParaWithCardId(productList.get(position).getId(), qty, String.valueOf(cartid));

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
            this.binding = binding;
        }
    }
}
