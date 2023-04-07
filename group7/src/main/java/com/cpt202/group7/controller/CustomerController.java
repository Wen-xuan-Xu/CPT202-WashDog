package com.cpt202.group7.controller;


import com.cpt202.group7.entity.User;
import com.cpt202.group7.service.CustomerLogin;
import com.cpt202.group7.service.CustomerServiceImpl;
import com.cpt202.group7.utils.customexceptions.UserAlreadyExistsException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class CustomerController {
    @Resource
    private CustomerServiceImpl customerService;
    private CustomerLogin customerLogin;
    @RequestMapping("/register")
    public String showRegisterForm() {
        return "/api/auth/register";
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam String gender,
                                      @RequestParam String nickname,
                                      @RequestParam String phone,
                                      Model model) {
        try {
            this.customerService.registerUser(username, password, nickname, gender, phone);
            //ra.addFlashAttribute("message", "Registration successful!");
            return "/api/auth/registerSuccess";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "/api/auth/register";
        }
    }


}




