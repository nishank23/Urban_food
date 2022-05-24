package com.example.urban_food.fragment.myorder;

import android.app.AlertDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.urban_food.Activities.OngoingOrder.OngoingOrderActivity;
import com.example.urban_food.Activities.PastOrder.OrderPresenter;
import com.example.urban_food.Activities.PastOrder.OrderView;
import com.example.urban_food.Activities.ShopsDetail.cart.CartPresenter;
import com.example.urban_food.Activities.ShopsDetail.cart.CartView;
import com.example.urban_food.Activities.ShopsDetail.cart.RvMenuInterface;
import com.example.urban_food.Adapter.CartAdapter;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Helper.GlobalData;
import com.example.urban_food.databinding.FragmentMyOrderBinding;
import com.example.urban_food.model.AddCart;
import com.example.urban_food.model.Cart;
import com.example.urban_food.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyOrder extends Fragment implements CartView, OrderView {
    FragmentMyOrderBinding binding;
    int cartProductId;
    int cartQty;
    CartAdapter cartAdapter;
    int cartId;
    float total;
    double lat,lon;
    CartPresenter cartPresenter = new CartPresenter(this);
    OrderPresenter presenter = new OrderPresenter(this);
    List<Cart> cartList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyOrderBinding.inflate(getLayoutInflater(), container, false);

        if (Common.isConnected()) {
            binding.layoutLoading.clLoading.setVisibility(View.VISIBLE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);
            binding.clActionBar.setVisibility(View.GONE);
            binding.cardItemsMyOrder.setVisibility(View.GONE);
            binding.buttonApplyPromoCode.setVisibility(View.GONE);
            binding.buttonContinue.setVisibility(View.GONE);
            binding.clBillList.setVisibility(View.GONE);
            binding.rvItemDetailsMyOrder.setVisibility(View.GONE);
            cartPresenter.getCallCart();
            binding.ivDeleteMyOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("Confirmation");
                    dialog.setMessage("Do you want to delete current cart");
                    dialog.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
                    dialog.setPositiveButton("Yes", (dialogInterface, i) -> {
                        cartPresenter.getClearCart();
                        GlobalData.Cart.clear();
                        dialogInterface.dismiss();
                    });
                    dialog.show();


                }
            });
            binding.buttonContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Location startPoint=new Location("locationA");

                    //start point
                    if(GlobalData.userAddressSelect==null)
                    {
                        startPoint.setLatitude(GlobalData.latitudeC);
                        startPoint.setLongitude(GlobalData.longitudeC);
                    }else{
                        startPoint.setLatitude(GlobalData.userAddressSelect.getLatitude());
                        startPoint.setLongitude(GlobalData.userAddressSelect.getLongitude());

                    }
                    //end point
                    Location endPoint=new Location("locationA");
                    endPoint.setLatitude(GlobalData.Cart.get(0).getProduct().getShop().getLatitude());
                    endPoint.setLongitude(GlobalData.Cart.get(0).getProduct().getShop().getLongitude());



                    double distance=startPoint.distanceTo(endPoint);
                    float distanceround = Math.round(distance * 100) / 100000;

                    if(distanceround>10){
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                        dialog.setTitle("Location is too far");
                        dialog.setMessage("This location is too far away from the restaurant to deliver.Please pick location near to the restaurant");
                        dialog.setPositiveButton("Ok", (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                        });
                        dialog.show();

                    }else if (total<1){
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                        dialog.setTitle("Add more items in the cart");
                        dialog.setMessage("Cart value should be equal to or more then 150");
                        dialog.setPositiveButton("Ok", (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                        });
                        dialog.show();
                    }else{
                        HashMap<String,String> map = new HashMap<>();
                        map.put("payment_mode","cash");

                        map.put("user_address_id",GlobalData.userAddressSelect.getId().toString());

                        presenter.orderplaced(map);
                    }
                    Log.d("distance",""+String.valueOf(distanceround));
                }
            });


        } else {

            binding.clActionBar.setVisibility(View.GONE);
            binding.cardItemsMyOrder.setVisibility(View.GONE);
            binding.buttonApplyPromoCode.setVisibility(View.GONE);
            binding.buttonContinue.setVisibility(View.GONE);
            binding.clBillList.setVisibility(View.GONE);
            binding.rvItemDetailsMyOrder.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutNodata.layoutNodata.setVisibility(View.GONE);
            binding.layoutNoInternet.layoutNoInternet.setVisibility(View.VISIBLE);
        }

        return binding.getRoot();
    }


    @Override
    public void onSuccessCartView(AddCart cartResponse) {

        GlobalData.Cart = cartResponse.getProducts();


        binding.tvPriceSubtotal.setText("₹ " + cartResponse.getTotalPrice().toString());

        float total = (float) cartResponse.getTotalPrice();
        float gst_cal = (float) (total * cartResponse.getTaxPercentage()) / 100;
        float commission = (total * cartResponse.getGrabitComission()) / 100;
        float commisionTax = (commission * cartResponse.getGrabitComissionTax()) / 100;

        float taxfee = gst_cal + commission + commisionTax;
        float taxfeeround = Math.round(taxfee * 100) / 100;

        binding.tvPriceTaxFee.setText("₹ " + taxfeeround);
        float finaltotal = total + taxfee + cartResponse.getDeliveryCharges();
        float round = Math.round(finaltotal * 100) / 100;
        Log.d("final", "" + String.valueOf(round));
        binding.tvPriceDiscount.setText("₹ " + "0");
        binding.buttonContinue.setText("Continue           " + "     " + "₹" + String.valueOf(round));


    }

    @Override
    public void onErrorCartView() {
        binding.clActionBar.setVisibility(View.GONE);
        binding.cardItemsMyOrder.setVisibility(View.GONE);
        binding.buttonApplyPromoCode.setVisibility(View.GONE);
        binding.buttonContinue.setVisibility(View.GONE);
        binding.clBillList.setVisibility(View.GONE);
        binding.rvItemDetailsMyOrder.setVisibility(View.GONE);
        binding.layoutError.clError.setVisibility(View.VISIBLE);
        binding.layoutLoading.clLoading.setVisibility(View.GONE);
        binding.layoutNodata.layoutNodata.setVisibility(View.GONE);
        binding.layoutNoInternet.layoutNoInternet.setVisibility(View.GONE);

    }

    @Override
    public void onSuccessGetCartView(AddCart getCartResponse) {
        cartList = getCartResponse.getProductList();
        GlobalData.Cart = getCartResponse.getProducts();
        if (getCartResponse.getProductList() == null) {
            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.VISIBLE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);
            binding.rvItemDetailsMyOrder.setVisibility(View.GONE);
            binding.clActionBar.setVisibility(View.GONE);
            binding.cardItemsMyOrder.setVisibility(View.GONE);
            binding.buttonApplyPromoCode.setVisibility(View.GONE);
            binding.buttonContinue.setVisibility(View.GONE);
            binding.clBillList.setVisibility(View.GONE);
        } else {

            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);

            binding.rvItemDetailsMyOrder.setVisibility(View.VISIBLE);
            binding.clActionBar.setVisibility(View.VISIBLE);
            binding.cardItemsMyOrder.setVisibility(View.VISIBLE);
            binding.buttonApplyPromoCode.setVisibility(View.VISIBLE);
            binding.buttonContinue.setVisibility(View.VISIBLE);
            binding.clBillList.setVisibility(View.VISIBLE);


            for (int i = 0; i < getCartResponse.getProductList().size(); i++) {
                binding.tvTitleShopName.setText(getCartResponse.getProductList().get(i).getProduct().getShop().getName());
                binding.tvShopLocation.setText(getCartResponse.getProductList().get(i).getProduct().getShop().getAddress());
            }
            Log.d("sizeCArt", "" + getCartResponse.getProductList().size());
            cartAdapter = new CartAdapter(getActivity(), cartList, new RvMenuInterface() {
                @Override
                public void cartParaWithCardId(int id, int value, String CartValue) {
                    cartProductId = id;
                    cartQty = value;
                    cartId = Integer.parseInt(CartValue);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("product_id", String.valueOf(cartProductId));
                    map.put("quantity", String.valueOf(cartQty));
                    map.put("cart_id", String.valueOf(cartId));
                    cartPresenter.callCart(map);
                }

                @Override
                public void cartPara(int id, int value) {
                }

                @Override
                public void clearCartPara(Boolean check, int id, int value) {
                }
            });
            binding.rvItemDetailsMyOrder.setAdapter(cartAdapter);
            binding.rvItemDetailsMyOrder.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.tvPriceSubtotal.setText("₹ " + getCartResponse.getTotalPrice().toString());

            total = (float) getCartResponse.getTotalPrice();
            float gst_cal = (total * getCartResponse.getTaxPercentage()) / 100;
            float commission = (total * getCartResponse.getGrabitComission()) / 100;
            float commisionTax = (commission * getCartResponse.getGrabitComissionTax()) / 100;

            float taxfee = gst_cal + commission + commisionTax;
            float taxfeeround = Math.round(taxfee * 100) / 100;

            binding.tvPriceTaxFee.setText("₹ " + taxfeeround);
            binding.tvPriceDelivery.setText("₹ " + getCartResponse.getDeliveryCharges().toString());

            float finaltotal = total + taxfee + getCartResponse.getDeliveryCharges();
            float round = Math.round(finaltotal * 100) / 100;
            binding.tvPriceDiscount.setText("₹ " + "0");
            binding.buttonContinue.setText("Continue           " + "     " + "₹" + String.valueOf(round));


        }
    }

    @Override
    public void onSuccessGetClearCartView(String message) {
        Toast.makeText(getActivity(), "Cart Deleted Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressShops() {
        binding.clActionBar.setVisibility(View.GONE);
        binding.cardItemsMyOrder.setVisibility(View.GONE);
        binding.buttonApplyPromoCode.setVisibility(View.GONE);
        binding.buttonContinue.setVisibility(View.GONE);
        binding.clBillList.setVisibility(View.GONE);


        binding.layoutError.clError.setVisibility(View.GONE);
        binding.layoutLoading.clLoading.setVisibility(View.VISIBLE);
        binding.layoutNodata.layoutNodata.setVisibility(View.GONE);
        binding.layoutNoInternet.layoutNoInternet.setVisibility(View.GONE);
    }

    @Override
    public void dismissProgressShops() {

        binding.clActionBar.setVisibility(View.VISIBLE);
        binding.cardItemsMyOrder.setVisibility(View.VISIBLE);
        binding.buttonApplyPromoCode.setVisibility(View.VISIBLE);
        binding.buttonContinue.setVisibility(View.VISIBLE);
        binding.clBillList.setVisibility(View.VISIBLE);


        binding.layoutError.clError.setVisibility(View.GONE);
        binding.layoutLoading.clLoading.setVisibility(View.GONE);
        binding.layoutNodata.layoutNodata.setVisibility(View.GONE);
        binding.layoutNoInternet.layoutNoInternet.setVisibility(View.GONE);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (Common.isConnected()) {
            cartPresenter.getCallCart();
        } else {

            binding.clActionBar.setVisibility(View.GONE);
            binding.cardItemsMyOrder.setVisibility(View.GONE);
            binding.buttonApplyPromoCode.setVisibility(View.GONE);
            binding.buttonContinue.setVisibility(View.GONE);
            binding.clBillList.setVisibility(View.GONE);


            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutNodata.layoutNodata.setVisibility(View.GONE);
            binding.layoutNoInternet.layoutNoInternet.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void getOrder(List<Order> orderList) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void getOrderId(int id) {

    }

    @Override
    public void getOrderIdSuccess(Order orderList) {
        Common.showToast("orderplaced Sucessfuully");
        startActivity(new Intent(getActivity(), OngoingOrderActivity.class));

    }

    @Override
    public void getOngoingOrder(List<Order> orderList) {

    }

}