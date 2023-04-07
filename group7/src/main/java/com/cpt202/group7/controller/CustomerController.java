package com.cpt202.group7.controller;


import com.cpt202.group7.service.CustomerServiceImpl;
import com.cpt202.group7.utils.customexceptions.UserAlreadyExistsException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/auth")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "api/auth/register";
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam String gender,
                                      @RequestParam String nickname
            //,RedirectAttributes ra
                                      ) {
        try {
            customerService.registerUser(username, password, gender, nickname);
            //ra.addFlashAttribute("message", "Registration successful!");
            return "redirect:/api/auth/registerSuccess";
        } catch (UserAlreadyExistsException e) {
            //ra.addFlashAttribute("error", e.getMessage());
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




