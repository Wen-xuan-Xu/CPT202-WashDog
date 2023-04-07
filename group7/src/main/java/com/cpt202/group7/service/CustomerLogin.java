package com.cpt202.group7.service;

import com.cpt202.group7.entity.User;
import com.cpt202.group7.mapper.CustomerMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CustomerLogin {
    @Resource
    private CustomerMapper customerMapper;

    public User LoginIn(String username, String password) {
        return customerMapper.getInfo(username,password);
    }


}
