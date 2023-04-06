package com.cpt202.group7.service;

import com.cpt202.group7.entity.User;
import com.cpt202.group7.enumerator.Gender;
import com.cpt202.group7.utils.customexceptions.*;


public interface CustomerService {
    void registerUser(String email, String password, Gender gender) throws UserAlreadyExistsException;

    User authenticateUser(String email, String password) throws UserNotFoundException, InvalidPasswordException;
}
