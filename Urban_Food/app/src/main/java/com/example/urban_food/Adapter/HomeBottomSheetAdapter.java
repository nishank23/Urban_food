package com.example.urban_food.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urban_food.Activites.Home.HomeActivity;
import com.example.urban_food.Activites.SplashScreen.SpalshInterface;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.Modal.ProfileModal.AddressesItem;
import com.example.urban_food.databinding.BottomsheetHomeLayoutBinding;
import com.example.urban_food.databinding.RecyclerBottomHomeBinding;

import java.util.List;

public class HomeBottomSheetAdapter extends RecyclerView.Adapter<HomeBottomSheetAdapter.Holder> {
    @NonNull
    Context context;
    List<AddressesItem> addressList;
    SpalshInterface callback;

    public HomeBottomSheetAdapter(@NonNull Context context, List<AddressesItem> addressList,SpalshInterface callback) {
        this.context = context;
        this.addressList = addressList;
        this.callback=callback;
    }

    public HomeBottomSheetAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(RecyclerBottomHomeBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeBottomSheetAdapter.Holder holder, int position) {
        holder.binding.tvAddress.setText(addressList.get(position).getMapAddress());
        Log.d("address",""+addressList.get(position).getMapAddress());
        holder.binding.tvAddress.setOnClickListener(view -> {
            GlobalData.longitude=addressList.get(position).getLongitude();
            GlobalData.latitude=addressList.get(position).getLatitude();
            callback.passer(true);
        });
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        RecyclerBottomHomeBinding binding;
        public Holder(RecyclerBottomHomeBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

    }
}
