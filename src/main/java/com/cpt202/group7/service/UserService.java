package com.cpt202.group7.service;

import com.cpt202.group7.entity.UserBean;
import com.cpt202.group7.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public UserBean LoginIn(String username, String password) {
        return userMapper.getInfo(username, password);
    }

    public void Insert(String username, String password) {
        userMapper.saveInfo(username, password);
    }
}


