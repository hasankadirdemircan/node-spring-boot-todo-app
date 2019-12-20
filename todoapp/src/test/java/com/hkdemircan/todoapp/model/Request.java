package com.hkdemircan.todoapp.model;

import com.hkdemircan.todoapp.dto.TodoDto;
import com.hkdemircan.todoapp.dto.UserDto;
import com.hkdemircan.todoapp.request.TodoRequest;
import com.hkdemircan.todoapp.request.UserRequest;

import java.util.Date;

public class Request {

    public UserRequest userSaveRequest(){
        UserRequest req = new UserRequest();
        UserDto dto = new UserDto();

        dto.setCreateBy("test");
        dto.setCreateDate(new Date());
        dto.setId(1);
        dto.setRole("admin");
        dto.setUsername("test");
        dto.setPassword("test");

        req.setUser(dto);

        return req;
    }

    public TodoRequest todoRequest(){
        TodoRequest todoRequest = new TodoRequest();
        TodoDto todoDto = new TodoDto();
        todoDto.setId(1);
        todoDto.setHeader("test");
        todoDto.setTodo("test");
        todoDto.setUsername("test");
        todoDto.setActive("X");
        todoDto.setCreateBy("test");
        todoDto.setCreateDate(new Date());
        todoRequest.setTodo(todoDto);
        return todoRequest;
    }
}
