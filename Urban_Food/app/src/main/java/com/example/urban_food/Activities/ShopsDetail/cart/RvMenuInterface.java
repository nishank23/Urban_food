package com.example.urban_food.Activities.ShopsDetail.cart;

public interface RvMenuInterface {
    void cartParaWithCardId(int id,int value,String CartValue);
    void cartPara(int id,int value);
    void clearCartPara(Boolean check,int id,int value);

}
