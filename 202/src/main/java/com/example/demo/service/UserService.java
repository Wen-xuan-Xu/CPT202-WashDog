package com.example.demo.service;

import com.example.demo.entity.UserBean;
import com.example.demo.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    //将dao层属性注入service层
    @Resource
    private UserMapper userMapper;

    public UserBean LoginIn(String username, String password) {
        return userMapper.getInfo(username,password);
    }

    public void Insert(String username,String password){
        userMapper.saveInfo(username, password);
    }
}


