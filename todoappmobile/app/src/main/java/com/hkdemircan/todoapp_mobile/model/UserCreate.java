package com.hkdemircan.todoapp_mobile.model;

public class UserCreate{
	private User user;

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"UserCreate{" + 
			"user = '" + user + '\'' + 
			"}";
		}
}
