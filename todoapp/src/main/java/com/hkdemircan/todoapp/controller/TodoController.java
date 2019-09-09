package com.hkdemircan.todoapp.controller;


import com.hkdemircan.todoapp.component.Authentication;
import com.hkdemircan.todoapp.enums.Error;
import com.hkdemircan.todoapp.request.TodoRequest;
import com.hkdemircan.todoapp.request.UserRequest;
import com.hkdemircan.todoapp.response.TodoListResponse;
import com.hkdemircan.todoapp.response.TodoResponse;
import com.hkdemircan.todoapp.response.UserResponse;
import com.hkdemircan.todoapp.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/todo")
@Api(value="Todo Management System", description="Operations pertaining to user in todo Management System")
public class TodoController {

    @Autowired
    TodoService todoService;

    @Autowired
    Authentication authentication;

    @PostMapping
    public TodoResponse saveTodo(@ApiParam(value = "Todo save object", required = true) @Valid @RequestBody TodoRequest req, HttpServletResponse httpRes){
        TodoResponse res = null;

        if(null == req.getTodo()){
            res = new TodoResponse(HttpServletResponse.SC_BAD_REQUEST, Error.ERR998, null);
        }else{
            try{
                req.getTodo().setUsername(authentication.getUsername());
                res = new TodoResponse(HttpServletResponse.SC_OK, null, todoService.saveTodo(req.getTodo()));

            }catch (Exception e){
                e.printStackTrace();
                res = new TodoResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,  Error.ERR000, null);
            }
        }
        httpRes.setStatus(res.getStatusCode());
        return res;
    }

    @GetMapping
    public TodoListResponse getTodos(HttpServletResponse httpRes){
        TodoListResponse res = null;

        try{
            res = new TodoListResponse(HttpServletResponse.SC_OK, null, todoService.getActiveTodos(authentication.getUsername(), "X"));
        }catch (Exception e){
            e.printStackTrace();
            res = new TodoListResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Error.ERR000, null);
        }
        httpRes.setStatus(res.getStatusCode());
        return res;
    }

    @GetMapping("/{id}")
    public TodoResponse getTodo(@PathVariable(value = "id") Integer id, HttpServletResponse httpRes){
        TodoResponse res = null;

        try{
            res = new TodoResponse(HttpServletResponse.SC_OK, null, todoService.getTodo(id));
        }catch (Exception e){
            e.printStackTrace();
            res = new TodoResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,  Error.ERR000, null);
        }
        httpRes.setStatus(res.getStatusCode());
        return res;
    }

    @PutMapping("/{id}")
    public TodoResponse getTodo(@PathVariable(value = "id") Integer id, @Valid @RequestBody TodoRequest req, HttpServletResponse httpRes){
        TodoResponse res = null;

        try{
            res = new TodoResponse(HttpServletResponse.SC_OK, null, todoService.saveTodo(req.getTodo()));
        }catch (Exception e){
            e.printStackTrace();
            res = new TodoResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,  Error.ERR000, null);
        }
        httpRes.setStatus(res.getStatusCode());
        return res;
    }
}
