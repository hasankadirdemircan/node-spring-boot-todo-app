package com.hkdemircan.todoapp.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hkdemircan.todoapp.dto.UserDto;
import com.hkdemircan.todoapp.response.base.BaseResponse;

public class UserResponse extends BaseResponse {


    public UserResponse(int statusCode, Error error, UserDto userDto) {
        super(statusCode, error);
        this.userDto = userDto;
    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDto userDto;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
