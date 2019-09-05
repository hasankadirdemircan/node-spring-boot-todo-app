package com.hkdemircan.todoapp.controller;


import com.hkdemircan.todoapp.request.TodoRequest;
import com.hkdemircan.todoapp.request.UserRequest;
import com.hkdemircan.todoapp.response.TodoResponse;
import com.hkdemircan.todoapp.response.UserResponse;
import com.hkdemircan.todoapp.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/todo")
@Api(value="Todo Management System", description="Operations pertaining to user in todo Management System")
public class TodoController {

    @Autowired
    TodoService todoService;

    @PostMapping("/save")
    public TodoResponse saveTodo(@ApiParam(value = "Todo save object", required = true) @Valid @RequestBody TodoRequest req, HttpServletResponse httpRes){
        TodoResponse res = null;

        res = new TodoResponse(HttpServletResponse.SC_OK, null, todoService.saveTodo(req.getTodo()));
        httpRes.setStatus(res.getStatusCode());
        return res;
    }
}
