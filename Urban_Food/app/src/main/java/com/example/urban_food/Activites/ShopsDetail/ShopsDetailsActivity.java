package com.example.urban_food.Activites.ShopsDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.urban_food.Activites.ShopsDetail.cart.CartPresenter;
import com.example.urban_food.Activites.ShopsDetail.cart.CartView;
import com.example.urban_food.Activites.ShopsDetail.cart.RvMenuInterface;
import com.example.urban_food.Adapter.MenuAdapter;
import com.example.urban_food.Helper.Common;
import com.example.urban_food.Modal.FavoriteModal.GetFavoriteResponse;
import com.example.urban_food.R;
import com.example.urban_food.databinding.ActivityShopsDetailsBinding;
import com.example.urban_food.fragment.favorite.FavoritePresenter;
import com.example.urban_food.fragment.favorite.FavoriteView;
import com.example.urban_food.model.AddCart;
import com.example.urban_food.model.Cart;
import com.example.urban_food.model.Category;
import com.example.urban_food.model.FavoriteList;
import com.example.urban_food.model.ShopDetail;

import java.util.HashMap;
import java.util.List;

public class ShopsDetailsActivity extends AppCompatActivity implements ShopDetailsView , CartView, FavoriteView {

    ActivityShopsDetailsBinding binding;
    String shopId="";
    String pathImage="";
    int cartProductId;
    int cartQty;
    int cartId;
    String favmsg ="";


    CartPresenter cartPresenter;
    ShopDetailsPresenter shopDetailsPresenter;
    FavoritePresenter favoritePresenter;
    boolean checker= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityShopsDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        shopId=getIntent().getStringExtra("ShopId");
        favoritePresenter = new FavoritePresenter(ShopsDetailsActivity.this);
        pathImage=getIntent().getStringExtra("pathImage");
        shopDetailsPresenter = new ShopDetailsPresenter(this);
        if(Common.isConnected()){

            HashMap<String,String> map=new HashMap();
            map.put("shop", shopId);
            map.put("user_id","1");
            shopDetailsPresenter.getShopDetails(map);


            binding.ivFavrouite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checker){
                        binding.ivFavrouite.setImageResource(R.drawable.ic_iconmonstr_bookmark_43);
                        favoritePresenter.addFavorite(shopId);
                       // Common.showToast(favmsg);
                        checker=false;
                    }
                    else{
                        binding.ivFavrouite.setImageResource(R.drawable.ic_favroite);
                        checker = true;
                    }
                }
            });

            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);

            binding.ivShopShopsDetail.setVisibility(View.VISIBLE);
            binding.ivBackShopsDetail.setVisibility(View.VISIBLE);
            binding.tvFullMenu.setVisibility(View.VISIBLE);
            binding.rvMenu.setVisibility(View.VISIBLE);
        }else{

            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.VISIBLE);


            binding.ivShopShopsDetail.setVisibility(View.GONE);
            binding.ivBackShopsDetail.setVisibility(View.GONE);
            binding.tvFullMenu.setVisibility(View.GONE);
            binding.rvMenu.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSuccessShopDetails(List<Category> shopDetailList) {
        if (shopDetailList.isEmpty()) {

            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.VISIBLE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);


            binding.ivShopShopsDetail.setVisibility(View.GONE);
            binding.ivBackShopsDetail.setVisibility(View.GONE);
            binding.tvFullMenu.setVisibility(View.GONE);
            binding.rvMenu.setVisibility(View.GONE);
        }else {

            binding.layoutLoading.clLoading.setVisibility(View.GONE);
            binding.layoutError.clError.setVisibility(View.GONE);
            binding.layoutNodata.clNoData.setVisibility(View.GONE);
            binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);


            binding.ivShopShopsDetail.setVisibility(View.VISIBLE);
            binding.ivBackShopsDetail.setVisibility(View.VISIBLE);
            binding.tvFullMenu.setVisibility(View.VISIBLE);
            binding.rvMenu.setVisibility(View.VISIBLE);

            Glide
                    .with(this)
                    .load(pathImage)
                    .into(binding.ivShopShopsDetail);

            MenuAdapter menuAdapter = new MenuAdapter(this, shopDetailList, new RvMenuInterface() {
                @Override
                public void cartParaWithCardId(int id, int value, String CartValue) {
                    cartProductId=id;
                    cartQty=value;
                    cartId=Integer.parseInt(CartValue);
                    cartPresenter=new CartPresenter(ShopsDetailsActivity.this);
                    HashMap<String,String> map=new HashMap<>();
                    map.put("product_id",String.valueOf(cartProductId));
                    map.put("quantity",String.valueOf(cartQty));
                    map.put("cart_id",String.valueOf(cartId));
                    cartPresenter.callCart(map);
                }

                @Override
                public void cartPara(int id, int value) {
                    cartProductId=id;
                    cartQty=value;
                    cartPresenter=new CartPresenter(ShopsDetailsActivity.this);
                    HashMap<String,String> map=new HashMap<>();
                    map.put("product_id",String.valueOf(cartProductId));
                    map.put("quantity",String.valueOf(cartQty));
                    cartPresenter.callCart(map);
                }

                @Override
                public void clearCartPara(Boolean check, int id, int value) {

                }


            });
            binding.rvMenu.setAdapter(menuAdapter);
            binding.rvMenu.setLayoutManager(new LinearLayoutManager(this));


        }
    }

    @Override
    public void onErrorShopDetails() {

        binding.layoutLoading.clLoading.setVisibility(View.GONE);
        binding.layoutError.clError.setVisibility(View.VISIBLE);
        binding.layoutNodata.clNoData.setVisibility(View.GONE);
        binding.layoutNoInternet.clNoInternet.setVisibility(View.GONE);


        binding.ivShopShopsDetail.setVisibility(View.GONE);
        binding.ivBackShopsDetail.setVisibility(View.GONE);
        binding.tvFullMenu.setVisibility(View.GONE);
        binding.rvMenu.setVisibility(View.GONE);
    }

    @Override
    public void showProgressShopDetails() {

    }

    @Override
    public void dismissShopDetails() {

    }

    @Override
    public void onSuccessCartView(List<Cart> cartResponse) {

    }

    @Override
    public void onErrorCartView() {

    }

    @Override
    public void onSuccessGetCartView(List<Cart> getCartResponse) {

    }

    @Override
    public void onSuccessGetClearCartView(String message) {

    }

    @Override
    public void showProgressShops() {

    }

    @Override
    public void dismissProgressShops() {

    }

    @Override
    public void onSuccessFavorite(String msg) {
        Toast.makeText(this, "Added as favourite", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getFavorite(FavoriteList response) {

    }
}