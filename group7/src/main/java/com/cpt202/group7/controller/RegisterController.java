package com.cpt202.group7.controller;


import com.cpt202.group7.entity.Customer;
import com.cpt202.group7.service.CustomerServiceImpl;
import com.cpt202.group7.utils.customexceptions.InvalidPasswordException;
import com.cpt202.group7.utils.customexceptions.UserAlreadyExistsException;
import com.cpt202.group7.utils.customexceptions.UserNotFoundException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/auth")
public class RegisterController {
    @Resource
    private CustomerServiceImpl customerService;

    // Register Page
    @GetMapping("/register")
    public String showRegisterForm() {
        return "api/auth/register";
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam String gender,
                                      @RequestParam String nickname,
                                      @RequestParam String phone,
                                      Model model) {
        // Register Successfully -> Jump to Home Page
        try {
            this.customerService.registerUser(username, password, nickname, gender, phone);
            return "redirect:/home";
        } catch (UserAlreadyExistsException e) {

            // Register Failed Since User Already Exists -> Stay in Register Page
            model.addAttribute("error", e.getMessage());
            return "redirect:/api/auth/register";
        }
    }
}




