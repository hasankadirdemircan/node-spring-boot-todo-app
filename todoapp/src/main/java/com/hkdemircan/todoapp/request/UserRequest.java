package com.hkdemircan.todoapp.request;

import com.hkdemircan.todoapp.dto.UserDto;
import com.hkdemircan.todoapp.request.base.BaseRequest;

public class UserRequest extends BaseRequest {

    private UserDto user;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
