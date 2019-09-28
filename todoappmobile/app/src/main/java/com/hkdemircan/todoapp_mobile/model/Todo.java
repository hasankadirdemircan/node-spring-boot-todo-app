package com.hkdemircan.todoapp_mobile.model;

public class Todo{
	private String todo;
	private String header;
	private String active;
	private String username;

	public void setTodo(String todo){
		this.todo = todo;
	}

	public String getTodo(){
		return todo;
	}

	public void setHeader(String header){
		this.header = header;
	}

	public String getHeader(){
		return header;
	}

	public void setActive(String active){
		this.active = active;
	}

	public String getActive(){
		return active;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
	public String toString() {
		return "Todo{" +
				"todo='" + todo + '\'' +
				", header='" + header + '\'' +
				", active='" + active + '\'' +
				", username='" + username + '\'' +
				'}';
	}
}
