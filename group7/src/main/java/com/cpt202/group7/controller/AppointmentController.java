package com.cpt202.group7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class AppointmentController {
    @GetMapping("/services")
    public String getServices(@RequestParam("petType") String petType, Model model) {
        List<String> services = new ArrayList<>();
        if (petType.equalsIgnoreCase("dog")) {
            services.add("Dog Grooming");
            services.add("Dog Boarding");
            services.add("Dog Training");
        } else if (petType.equalsIgnoreCase("cat")) {
            services.add("Cat Grooming");
            services.add("Cat Boarding");
            services.add("Cat Training");
        } else {
            services.add("No services available");
        }
        model.addAttribute("services", services);
        return "services :: servicesList";
    }

    @GetMapping("order")
    public String getOrder(){
        return " content";
    }
}
