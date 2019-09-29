package com.hkdemircan.todoapp_mobile.model;

import java.util.List;

public class GetOneTodo {
    private TodosItem todo;
    private int statusCode;

    public TodosItem getTodo() {
        return todo;
    }

    public void setTodo(TodosItem todo) {
        this.todo = todo;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
