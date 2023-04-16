package com.cpt202.group7.controller;

import com.cpt202.group7.entity.service;
import com.cpt202.group7.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @RequestMapping("/list")
    public String showServices(Model model)
    {
        List<service> services = serviceService.getServiceList();
        model.addAttribute("service",services);
        return "/service/ServiceList";
    }

    @PostMapping("/delete")
    public String deleteService(@RequestParam Integer serviceId){
        serviceService.deleteService(serviceId);
        return "redirect:/admin/service/list";
    }



    @PostMapping("/update")
    public String updateServiceDetails(@ModelAttribute("service") service service){
        serviceService.updateService(service);
        return "redirect:/admin/service/list";
    }

    @RequestMapping("/add")
    public String showAddServicePage(Model model){
        model.addAttribute("service", new service());
        return "/service/addService";
    }

    @PostMapping("/insert")
    public String insertService(@ModelAttribute("service") service service){
        serviceService.insert(service);
        return "redirect:/admin/service/list";
    }
}
