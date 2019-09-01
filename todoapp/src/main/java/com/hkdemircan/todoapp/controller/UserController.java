package com.hkdemircan.todoapp.controller;

import com.hkdemircan.todoapp.request.UserRequest;
import com.hkdemircan.todoapp.response.UserResponse;
import com.hkdemircan.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/user")
    public UserResponse createUser(@Valid @RequestBody UserRequest req, HttpServletResponse httpRes){
        UserResponse res = null;

        res = new UserResponse(HttpServletResponse.SC_OK, null, userService.saveUser(req.getUser()));
        httpRes.setStatus(res.getStatusCode());
        return res;
    }
}
