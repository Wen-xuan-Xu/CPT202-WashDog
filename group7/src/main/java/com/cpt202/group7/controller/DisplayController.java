package com.cpt202.group7.controller;


import com.cpt202.group7.entity.Service;
import com.cpt202.group7.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer/service")
public class DisplayController {
    @Autowired
    private ServiceService serviceService;

    @RequestMapping("/display")
    public String displayServices(Model model)
    {
        List<Service> services = serviceService.getServiceList();
        model.addAttribute("service",services);
        return "/service/serviceDisplay";
    }
}
