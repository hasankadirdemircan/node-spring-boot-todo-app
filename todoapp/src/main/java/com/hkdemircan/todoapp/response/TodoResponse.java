package com.hkdemircan.todoapp.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hkdemircan.todoapp.dto.TodoDto;
import com.hkdemircan.todoapp.dto.UserDto;
import com.hkdemircan.todoapp.enums.Error;
import com.hkdemircan.todoapp.response.base.BaseResponse;

public class TodoResponse extends BaseResponse {

    public TodoResponse(int statusCode, Error error, TodoDto todo) {
        super(statusCode, error);
        this.todo = todo;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TodoDto todo;

    public TodoDto getTodo() {
        return todo;
    }

    public void setTodo(TodoDto todo) {
        this.todo = todo;
    }
}
