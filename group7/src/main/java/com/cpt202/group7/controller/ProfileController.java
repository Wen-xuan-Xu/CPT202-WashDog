package com.cpt202.group7.controller;

import com.cpt202.group7.entity.User;
import com.cpt202.group7.service.Interface.ProfileService;
import com.cpt202.group7.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{userId}/profile")
    public String showUserProfile(@PathVariable("userId") Integer userId, Model model) {
        User user = profileService.getUserById(userId);
        model.addAttribute("user", user);
        return "userProfile";
    }

    @GetMapping("/{userId}/editProfile")
    public String showEditProfile(@PathVariable("userId") Integer userId, Model model) {
        User user = profileService.getUserById(userId);
        model.addAttribute("user", user);
        return "editProfile";
    }

    @PostMapping("/{userId}/updateProfile")
    public String updateProfile(@PathVariable("userId") Integer userId, @ModelAttribute("user") User user) {
        profileService.updateUserProfile(user);
        System.out.println("123");
        return "redirect:/customer/" + userId + "/profile";
    }













}
