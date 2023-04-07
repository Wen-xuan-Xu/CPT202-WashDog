package com.cpt202.group7.controller;

import com.cpt202.group7.service.CustomerServiceImpl;
import com.cpt202.group7.utils.customexceptions.InvalidPasswordException;
import com.cpt202.group7.utils.customexceptions.UserNotFoundException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/auth")
public class LoginController {
    @Resource
    private CustomerServiceImpl customerService;

    @GetMapping("/login")
    public String showLoginPage(){
        return "/api/auth/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model){
        try{
            this.customerService.authenticateUser(username, password);
            System.out.println("Login Success");
            return "redirect:/home";
        } catch (UserNotFoundException | InvalidPasswordException e) {
            model.addAttribute("error",e.getMessage());
            return "redirect:/api/auth/login";
        }
    }

}
