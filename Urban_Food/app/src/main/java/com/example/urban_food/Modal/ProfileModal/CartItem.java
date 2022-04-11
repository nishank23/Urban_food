package com.example.urban_food.Modal.ProfileModal;

import java.util.List;

public class CartItem{
	private Object note;
	private Product product;
	private int quantity;
	private int savedforlater;
	private Object missingBy;
	private int productId;
	private int missing;
	private int id;
	private int productPricesId;
	private Object orderId;
	private Object promocodeId;
	private List<Object> cartAddons;

	public Object getNote(){
		return note;
	}

	public Product getProduct(){
		return product;
	}

	public int getQuantity(){
		return quantity;
	}

	public int getSavedforlater(){
		return savedforlater;
	}

	public Object getMissingBy(){
		return missingBy;
	}

	public int getProductId(){
		return productId;
	}

	public int getMissing(){
		return missing;
	}

	public int getId(){
		return id;
	}

	public int getProductPricesId(){
		return productPricesId;
	}

	public Object getOrderId(){
		return orderId;
	}

	public Object getPromocodeId(){
		return promocodeId;
	}

	public List<Object> getCartAddons(){
		return cartAddons;
	}
}