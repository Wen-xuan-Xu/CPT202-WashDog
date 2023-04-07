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
public class CustomerController {
    @Resource
    private CustomerServiceImpl customerService;

    // Register
    @RequestMapping("/register")
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
        try {
            this.customerService.registerUser(username, password, nickname, gender, phone);
            //ra.addFlashAttribute("message", "Registration successful!");
            return "redirect:/api/auth/registerSuccess";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/api/auth/registerFailed";
        }
    }

    @GetMapping("/registerSuccess")
    public String showSuccessPage() {
        return "api/auth/registerSuccess";
    }

    @GetMapping("/registerFailed")
    public String showFailedPage() {
        return "api/auth/registerFailed";
    }

    // Login
    @RequestMapping("/login")
    public String showLoginPage(){
        return "/api/auth/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model){
        try{
            this.customerService.authenticateUser(username, password);
            return "index.html";
        } catch (UserNotFoundException | InvalidPasswordException e) {
            model.addAttribute("error",e.getMessage());
            return "redirect:/api/auth/login";
        }

    }

}




