package com.cpt202.group7.controller;

import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.entity.Service;
import com.cpt202.group7.entity.User;
import com.cpt202.group7.service.AdminService;
import com.cpt202.group7.service.PetService;
import com.cpt202.group7.service.ServiceService;
import com.cpt202.group7.service.UserService;
import com.cpt202.group7.utils.customexceptions.UserAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        try {
            userService.saveUser(user);
            return "redirect:/login";
        }catch (UserAlreadyExistsException e){
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping("/admin/dashboard")
    public String adminHomePage(Model model, HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        session.setAttribute("userid",Integer.toString(userService.getCurrentUserID()));
        session.setAttribute("username",username);
        model.addAttribute("userid",session.getAttribute("userid"));
        model.addAttribute("username",session.getAttribute("username"));


        Map<String, Object> stats = adminService.getDashboardStats();
        model.addAllAttributes(stats);
        return "/admin/dashboard";
    }

    @RequestMapping("/customer/dashboard")
    public String customerHomePage(Model model,HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        session.setAttribute("userid",Integer.toString(userService.getCurrentUserID()));
        session.setAttribute("username",username);
        model.addAttribute("username",session.getAttribute("username"));
        model.addAttribute("userPhoto",userService.getCurrentUserPhoto());
        System.out.println(userService.getCurrentUserPhoto());
        List<Service> recommendedServices = serviceService.getRandomAllowedServices();
        model.addAttribute("recommendedServices", recommendedServices);
        // 打印获取到的随机服务数量
        System.out.println("Number of random services: " + recommendedServices.size());
        return "/customer/base";
    }

//    @GetMapping("/user-profile")
//    public String userProfile(@RequestParam("username") String username, Model model) {
//        User user = userService.findByUsername(username);
//        model.addAttribute("user", user);
//        return "user-profile";
//    }
//
//    @GetMapping("/user-profile")
//    public String userProfile(HttpSession session,Model model) {
//        User user = userService.findByUsername(session.getAttribute("username").toString());
//        model.addAttribute("user", user);
//        return "user-profile";
//    }
//
//    @GetMapping("/index")
//    public String commons() {
//        return "index";
//    }
//
//    @GetMapping("/registerFailed")
//    public String registerFailed() {
//        return "registerFailed";
//    }
//
//
//    @PostMapping("/update-user")
//    public String updateUser(User user, Model model) {
//        userService.updateUser(user);
//        model.addAttribute("user", user);
//        return "redirect:/user-profile?username=" + user.getUsername();
//    }
//
//    @RequestMapping("/customer/dashboard/pet")
//    public String customerPetPage(Model model,HttpSession session){
//        model.addAttribute("username",session.getAttribute("userid"));
//        return"helloCustomer";
//    }


}
