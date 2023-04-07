package com.cpt202.group7.service;

import com.cpt202.group7.entity.User;
import com.cpt202.group7.utils.customexceptions.InvalidPasswordException;
import com.cpt202.group7.utils.customexceptions.UserAlreadyExistsException;
import com.cpt202.group7.utils.customexceptions.UserNotFoundException;


public interface CustomerService {
    void registerUser(String username, String password, String nickname,String gender,String phone) throws UserAlreadyExistsException;

    User authenticateUser(String username, String password) throws UserNotFoundException, InvalidPasswordException;
}
