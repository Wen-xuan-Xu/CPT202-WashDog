package com.cpt202.group7.controller;

import com.cpt202.group7.entity.Customer;
import com.cpt202.group7.enumerator.Gender;
import com.cpt202.group7.service.CustomerService;
import com.cpt202.group7.utils.customexceptions.InvalidPasswordException;
import com.cpt202.group7.utils.customexceptions.UserAlreadyExistsException;
import com.cpt202.group7.utils.customexceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/auth")
public class CustomerController {
    // 将Service注入Web层
    @Autowired
    CustomerService customerService;

    @PostMapping("/register")
    public String registerUser(@RequestParam String email, @RequestParam String password, @RequestParam Gender gender) {
        try {
            customerService.registerUser(email, password, gender);
            return "User registered successfully";
        } catch (UserAlreadyExistsException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password){
        try{
            Customer customer = customerService.authenticateUser(email, password);
            customerService.authenticateUser(email,password);
            return "Welcome" + customer.getNickname();
        } catch (UserNotFoundException | InvalidPasswordException e) {
            return e.getMessage();
        }
    }
}
