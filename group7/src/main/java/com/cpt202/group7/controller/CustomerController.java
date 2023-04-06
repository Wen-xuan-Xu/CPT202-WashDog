package com.cpt202.group7.controller;

import com.cpt202.group7.service.CustomerService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/auth")
public class CustomerController {
    // 将Service注入Web层
    @Autowired
    CustomerService userService;

    @PostMapping("/register")
    public void a(){

    }

}
