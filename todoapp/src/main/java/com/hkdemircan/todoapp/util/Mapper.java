package com.hkdemircan.todoapp.util;

import com.hkdemircan.todoapp.dto.UserDto;
import com.hkdemircan.todoapp.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {

    @Autowired
    private ModelMapper modelMapper;

    public User dto2Model(UserDto dto){
        return dto2Model(dto, null);
    }

    public User dto2Model(UserDto dto, User model){
        if(model == null){
            model = new User();
        }

        // converting UserDto to User object
        model = modelMapper.map(dto, User.class);

        //BeanUtils.copyProperties(dto, model);

      /*  model.setId(dto.getId());
        model.setUsername(dto.getUsername());
        model.setPassword(dto.getPassword());
        model.setCreateBy(dto.getCreateBy());
        model.setCreateDate(dto.getCreateDate());
        model.setRole(dto.getRole());
*/
        return model;
    }

    public UserDto model2Dto(User model) {
        return model2Dto(model, null);
    }
    public UserDto model2Dto(User model, UserDto dto) {
        if(dto == null) {
            dto = new UserDto();
        }

        dto = modelMapper.map(model, UserDto.class);

        //BeanUtils.copyProperties(model, dto);
        /*
        dto.setId(model.getId());
        dto.setUsername(model.getUsername());
        dto.setPassword(model.getPassword());
        dto.setCreateBy(model.getCreateBy());
        dto.setCreateDate(model.getCreateDate());
        dto.setRole(model.getRole());
*/
        return dto;

    }
}
