package com.hkdemircan.todoapp.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hkdemircan.todoapp.dto.TodoDto;
import com.hkdemircan.todoapp.enums.Error;
import com.hkdemircan.todoapp.response.base.BaseResponse;

import java.util.List;

public class TodoListResponse extends BaseResponse {


    public TodoListResponse(int statusCode, Error error, List<TodoDto> todos) {
        super(statusCode, error);
        this.todos = todos;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private  List<TodoDto> todos;

    public List<TodoDto> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoDto> todos) {
        this.todos = todos;
    }
}
