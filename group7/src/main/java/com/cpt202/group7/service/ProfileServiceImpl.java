package com.cpt202.group7.service;

import com.cpt202.group7.entity.User;
import com.cpt202.group7.mapper.UserMapper;
import com.cpt202.group7.service.Interface.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public void updateUserProfile(User user) {
        userMapper.updateById(user);
    }
}
