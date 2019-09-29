package com.hkdemircan.todoapp_mobile.model;

public class TodosItem{
	private String todo;
	private String createBy;
	private String header;
	private String active;
	private int id;
	private String createDate;
	private String username;

	public void setTodo(String todo){
		this.todo = todo;
	}

	public String getTodo(){
		return todo;
	}

	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}

	public String getCreateBy(){
		return createBy;
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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	public String getCreateDate(){
		return createDate;
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
			"TodosItem{" + 
			"todo = '" + todo + '\'' + 
			",createBy = '" + createBy + '\'' + 
			",header = '" + header + '\'' + 
			",active = '" + active + '\'' + 
			",id = '" + id + '\'' + 
			",createDate = '" + createDate + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
