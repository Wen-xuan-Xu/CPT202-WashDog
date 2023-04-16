package com.cpt202.group7.controller;

import com.cpt202.group7.entity.User;
import com.cpt202.group7.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class ProfileController {

    @Autowired
    private UserService userService;
    @RequestMapping("/user-profile")
    public String userProfile(@RequestParam("username") String username, Model model) {
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "user-profile";
    }


//    @GetMapping("/user-profile")
//    public String userProfile(HttpSession session, Model model) {
//        User user = userService.findByUsername(session.getAttribute("username").toString());
//        model.addAttribute("user", user);
//        return "user-profile";
//    }

    @GetMapping("/index")
    public String commons() {
        return "index";
    }

    @GetMapping("/registerFailed")
    public String registerFailed() {
        return "registerFailed";
    }

    @RequestMapping("/update-user")
    public String updateUser(User user, Model model) {
        userService.updateUser(user);
        model.addAttribute("user", user);
        return "redirect:/customer/user-profile?username=" + user.getUsername();
    }
}
