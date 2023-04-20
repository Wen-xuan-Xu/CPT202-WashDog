package com.cpt202.group7.service.Interface;

import com.cpt202.group7.entity.User;

public interface ProfileService {
    User getUserById(Integer userId);

    void updateUserProfile(User user);
}
