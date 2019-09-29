package com.hkdemircan.todoapp_mobile.model;

import java.util.List;

public class GetTodo{
	private List<TodosItem> todos;
	private int statusCode;

	public void setTodos(List<TodosItem> todos){
		this.todos = todos;
	}

	public List<TodosItem> getTodos(){
		return todos;
	}

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}

	public int getStatusCode(){
		return statusCode;
	}

	@Override
 	public String toString(){
		return 
			"GetTodo{" + 
			"todos = '" + todos + '\'' + 
			",statusCode = '" + statusCode + '\'' + 
			"}";
		}
}