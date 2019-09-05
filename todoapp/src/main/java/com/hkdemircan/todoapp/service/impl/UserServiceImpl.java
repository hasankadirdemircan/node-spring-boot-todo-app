package com.hkdemircan.todoapp.service.impl;

import com.hkdemircan.todoapp.dto.UserDto;
import com.hkdemircan.todoapp.model.User;
import com.hkdemircan.todoapp.repository.UserRepository;
import com.hkdemircan.todoapp.service.UserService;
import com.hkdemircan.todoapp.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Mapper mapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
       // before updating
        //maybe validation aria
       // User user = Mapper.dto2Model(userDto);
        //maybe user.setPassword
       // return Mapper.model2Dto(userRepository.save(user));

        User user = mapper.dto2Model(userDto);
        return mapper.model2Dto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return null;
    }
}
