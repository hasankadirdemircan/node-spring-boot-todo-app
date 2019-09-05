package com.hkdemircan.todoapp.service;

import com.hkdemircan.todoapp.dto.UserDto;

public interface UserService {

    UserDto saveUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
}
