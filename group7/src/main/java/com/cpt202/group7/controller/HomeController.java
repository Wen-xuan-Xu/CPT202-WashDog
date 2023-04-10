package com.cpt202.group7.controller;

import jakarta.servlet.http.HttpSession;
import org.apache.catalina.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showHomepage(HttpSession session){
        return "/Homepage";
    }
}
