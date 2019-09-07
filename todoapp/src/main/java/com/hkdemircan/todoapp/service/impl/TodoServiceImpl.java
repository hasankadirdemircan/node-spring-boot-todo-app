package com.hkdemircan.todoapp.service.impl;

import com.hkdemircan.todoapp.dto.TodoDto;
import com.hkdemircan.todoapp.model.Todo;
import com.hkdemircan.todoapp.repository.TodoRepository;
import com.hkdemircan.todoapp.service.TodoService;
import com.hkdemircan.todoapp.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    Mapper mapper;

    @Override
    public TodoDto saveTodo(TodoDto todoDto) {

        Todo todo = mapper.dto2Model(todoDto);

        return mapper.model2Dto(todoRepository.save(todo));
    }

    @Override
    public List<TodoDto> getActiveTodos(String username, String status) {
        return mapper.model2DtoTodos(todoRepository.findAllByCreateByAndActive(username, status));
    }

}
