package com.hkdemircan.todoapp.service;

import com.hkdemircan.todoapp.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto saveTodo(TodoDto todoDto);
    List<TodoDto> getActiveTodos(String username, String status);
}
