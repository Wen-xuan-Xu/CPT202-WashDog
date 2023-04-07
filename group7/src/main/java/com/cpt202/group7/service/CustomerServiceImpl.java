package com.cpt202.group7.service;

import com.cpt202.group7.entity.Customer;

import com.cpt202.group7.mapper.CustomerMapper;
import com.cpt202.group7.utils.customexceptions.InvalidPasswordException;
import com.cpt202.group7.utils.customexceptions.UserAlreadyExistsException;
import com.cpt202.group7.utils.customexceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void registerUser(String username, String password, String nickname,String gender,String phone) throws UserAlreadyExistsException {
        Customer existingUser = customerMapper.findByEmail(username);

        if(existingUser!=null){
            throw new UserAlreadyExistsException("User with email " + username + " already exists");
        }
        customerMapper.insertOne(username, password, nickname, gender, phone);
    }

    @Override
    public Customer authenticateUser(String username, String password) throws UserNotFoundException, InvalidPasswordException {
        System.out.println(username + "\t"+password);
        Customer customer = customerMapper.findByEmail(username);
        System.out.println(customer);
        if(customer == null){
            throw new UserNotFoundException("User with email " + username+ " not found");
        }

        // Add Web Security!!!
        if(!password.equals(customer.getPassword())){
            throw new InvalidPasswordException("Invalid password");
        }

        return customer;
    }

}
