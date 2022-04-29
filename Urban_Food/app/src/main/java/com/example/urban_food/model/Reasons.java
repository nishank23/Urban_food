package com.example.urban_food.model;

import com.google.gson.annotations.SerializedName;

public class Reasons{

	@SerializedName("id")
	private int id;

	@SerializedName("help_quest")
	private String helpQuest;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setHelpQuest(String helpQuest){
		this.helpQuest = helpQuest;
	}

	public String getHelpQuest(){
		return helpQuest;
	}

	@Override
 	public String toString(){
		return 
			"Reasons{" + 
			"id = '" + id + '\'' + 
			",help_quest = '" + helpQuest + '\'' + 
			"}";
		}
}