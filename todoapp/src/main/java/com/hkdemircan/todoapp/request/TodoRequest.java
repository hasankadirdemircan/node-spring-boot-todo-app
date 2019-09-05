package com.hkdemircan.todoapp.request;

import com.hkdemircan.todoapp.dto.TodoDto;
import com.hkdemircan.todoapp.request.base.BaseRequest;

public class TodoRequest extends BaseRequest {

    private TodoDto todo;

    public TodoDto getTodo() {
        return todo;
    }

    public void setTodo(TodoDto todo) {
        this.todo = todo;
    }
}
