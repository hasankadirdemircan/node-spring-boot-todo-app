package com.hkdemircan.todoapp_mobile.model;

public class User{
	private String password;
	private String role;
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"password = '" + password + '\'' + 
			",role = '" + role + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
