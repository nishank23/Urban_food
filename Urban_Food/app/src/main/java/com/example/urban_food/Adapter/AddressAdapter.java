package com.example.urban_food.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urban_food.Activities.ManageAddress.AddressInterface;
import com.example.urban_food.Activities.ShopsDetail.ShopsDetailsActivity;
import com.example.urban_food.R;
import com.example.urban_food.databinding.RecyclerAddresslayoutBinding;
import com.example.urban_food.model.Address;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyHolder> {
    Activity context;
    List<Address> addressList;
    AddressInterface addressInterface;

    public AddressAdapter(Activity context, List<Address> addressList,AddressInterface addressInterface) {
        this.context = context;
        this.addressList = addressList;
        this.addressInterface = addressInterface;
    }


    @NonNull
    @Override
    public AddressAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(RecyclerAddresslayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.MyHolder holder, int position) {
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
        holder.binding.btnEdit.setOnClickListener(view -> {
            addressInterface.editClick(addressList.get(position));

        });
        holder.binding.btnDelete.setOnClickListener(view -> {
            android.app.AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("Delete Address");
            dialog.setMessage("Do you want to delete the Address");
            dialog.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
            dialog.setPositiveButton("Yes", (dialogInterface, i) -> {

                addressInterface.deleteClick(String.valueOf(addressList.get(position).getId()));
                addressList.remove(position);
                notifyDataSetChanged();
                dialogInterface.dismiss();
            });
            dialog.show();
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
