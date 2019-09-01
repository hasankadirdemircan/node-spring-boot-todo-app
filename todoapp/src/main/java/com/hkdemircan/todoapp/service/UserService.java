package com.hkdemircan.todoapp.service;

import com.hkdemircan.todoapp.dto.UserDto;

public interface UserService {

    public UserDto saveUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto);
}
