package com.hkdemircan.todoapp.controller;

import com.hkdemircan.todoapp.enums.Error;
import com.hkdemircan.todoapp.request.UserRequest;
import com.hkdemircan.todoapp.response.UserResponse;
import com.hkdemircan.todoapp.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(value="User Management System", description="Operations pertaining to user in User Management System")
public class UserController {

    @Autowired
    UserService userService;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @ApiOperation(value = "View a list of available employees", response = UserResponse.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful Sign Up User"),
                    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            }
    )
    @PostMapping
    public UserResponse createUser(@ApiParam(value = "User create object", required = true) @Valid @RequestBody UserRequest req, HttpServletResponse httpRes){
        UserResponse res = null;

        if(null == req.getUser()){
            res = new UserResponse(HttpServletResponse.SC_BAD_REQUEST, Error.ERR998, null);
        }else{
            try{
                req.getUser().setPassword(bCryptPasswordEncoder.encode(req.getUser().getPassword()));
                res = new UserResponse(HttpServletResponse.SC_OK, null, userService.saveUser(req.getUser()));

            }catch (Exception e){
                e.printStackTrace();
                res = new UserResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Error.ERR000, null);
            }
        }

        httpRes.setStatus(res.getStatusCode());
        return res;
    }
}
