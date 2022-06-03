package com.example.urban_food.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.R;
import com.example.urban_food.databinding.RecyclerAddresslayoutBinding;
import com.example.urban_food.model.Address;

import java.util.List;

public class SelectAddressAdapter extends RecyclerView.Adapter<SelectAddressAdapter.MyHolder> {
    Activity context;
    List<Address> addressList;

    public SelectAddressAdapter(Activity context, List<Address> addressList) {
        this.context = context;
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public SelectAddressAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SelectAddressAdapter.MyHolder(RecyclerAddresslayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SelectAddressAdapter.MyHolder holder, int position) {
        holder.binding.btnEdit.setVisibility(View.GONE);
        holder.binding.btnDelete.setVisibility(View.GONE);
        if (addressList.get(position).getType().equalsIgnoreCase("work")) {
            holder.binding.addlogo.setImageResource(R.drawable.ic_baseline_work_24);
            holder.binding.tvAddressType.setText(addressList.get(position).getType());
        } else if (addressList.get(position).getType().equalsIgnoreCase("home")) {
            holder.binding.addlogo.setImageResource(R.drawable.ic_baseline_home_24);
            holder.binding.tvAddressType.setText(addressList.get(position).getType());


        } else {
            holder.binding.tvAddressType.setText(addressList.get(position).getType());
            holder.binding.addlogo.setImageResource(R.drawable.ic_locationnew);

        }
        holder.binding.tvAddress.setText(addressList.get(position).getMapAddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalData.longitude=addressList.get(holder.getAbsoluteAdapterPosition()).getLongitude();
                GlobalData.latitude=addressList.get(holder.getAbsoluteAdapterPosition()).getLatitude();
                GlobalData.longitudeC=addressList.get(holder.getAbsoluteAdapterPosition()).getLongitude();
                GlobalData.latitudeC=addressList.get(holder.getAbsoluteAdapterPosition()).getLatitude();
                GlobalData.userAddressSelect = addressList.get(holder.getAbsoluteAdapterPosition());
                Log.d("let_long_sel",""+GlobalData.longitudeC+" "+GlobalData.latitudeC + " "+GlobalData.userAddressSelect.toString());
                context.finish();

            }
        });
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        RecyclerAddresslayoutBinding binding;

        public MyHolder(RecyclerAddresslayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
