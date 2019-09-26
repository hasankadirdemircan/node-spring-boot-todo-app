package com.hkdemircan.todoapp.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hkdemircan.todoapp.dto.UserDto;
import com.hkdemircan.todoapp.enums.Error;
import com.hkdemircan.todoapp.response.base.BaseResponse;

public class UserResponse extends BaseResponse {

	private static final long serialVersionUID = -774947718002386439L;

	public UserResponse(int statusCode, Error error, UserDto user) {
        super(statusCode, error);
        this.user = user;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDto user;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
