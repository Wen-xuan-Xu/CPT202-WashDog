package com.cpt202.group7.service.Interface;

import com.cpt202.group7.entity.User;
import com.cpt202.group7.utils.customexceptions.InvalidPasswordException;

public interface ProfileService {
    User getUserById(Integer userId);

    void updateUserProfile(User user);

    void updateUserPassword(Integer userId, String oldPassword, String newPassword) throws InvalidPasswordException;
}
