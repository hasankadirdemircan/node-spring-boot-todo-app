package com.hkdemircan.todoapp.util;

import com.hkdemircan.todoapp.dto.UserDto;
import com.hkdemircan.todoapp.model.User;

public class Mapper {

    public static User dto2Model(UserDto dto){
        return dto2Model(dto, null);
    }

    public static User dto2Model(UserDto dto, User model){
        if(model == null){
            model = new User();
        }

        model.setId(dto.getId());
        model.setUsername(dto.getUsername());
        model.setPassword(dto.getPassword());
        model.setCreateBy(dto.getCreateBy());
        model.setCreateDate(dto.getCreateDate());

        return model;
    }

    public static UserDto model2Dto(User model) {
        return model2Dto(model, null);
    }
    public static UserDto model2Dto(User model, UserDto dto) {
        if(dto == null) {
            dto = new UserDto();
        }
        dto.setId(model.getId());
        dto.setUsername(model.getUsername());
        dto.setPassword(model.getPassword());
        dto.setCreateBy(model.getCreateBy());
        dto.setCreateDate(model.getCreateDate());

        return dto;

    }
}
