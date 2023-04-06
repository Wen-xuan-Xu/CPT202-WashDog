package com.cpt202.group7.service;

import com.cpt202.group7.entity.Customer;
import com.cpt202.group7.enumerator.Gender;
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
    public void registerUser(String email, String password, Gender gender) throws UserAlreadyExistsException {
        Customer existingUser = customerMapper.findByEmail(email);
        if(existingUser!=null){
            throw new UserAlreadyExistsException("User with email " + email + " already exists");
        }

        Customer user = new Customer(email, password,gender);
        customerMapper.save(user);
    }

    @Override
    public Customer authenticateUser(String email, String password) throws UserNotFoundException, InvalidPasswordException {
        Customer customer = customerMapper.findByEmail(email);
        if(customer == null){
            throw new UserNotFoundException("User with email " + email + " not found");
        }

        // Add Web Security!!!
        if(!password.equals(customer.getPassword())){
            throw new InvalidPasswordException("Invalid password");
        }

        return customer;
    }

}
