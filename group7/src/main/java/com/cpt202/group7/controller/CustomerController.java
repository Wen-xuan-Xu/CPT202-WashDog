package com.cpt202.group7.controller;


import com.cpt202.group7.service.CustomerServiceImpl;
import com.cpt202.group7.utils.customexceptions.UserAlreadyExistsException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/auth")
public class CustomerController {
    @Resource
    private CustomerServiceImpl customerService;

    @RequestMapping("/register")
    public String showRegisterForm() {
        return "api/auth/register.html";
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
}




