
package com.cpt202.group7.controller;

import com.cpt202.group7.entity.Service;
import com.cpt202.group7.mapper.ServiceMapper;
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

    @Autowired
    private ServiceMapper serviceMapper;

    @RequestMapping("/list")
    public String showServices(Model model)
    {
        List<Service> services = serviceService.getServiceList();
        model.addAttribute("service",services);
        return "/service/ServiceList";
    }

    @PostMapping("/delete")
    public String deleteService(@RequestParam Integer serviceId){
        serviceService.deleteService(serviceId);
        return "redirect:/admin/service/list";
    }


    @GetMapping("/update")
    public String showServiceUpdateForm(@RequestParam Integer serviceId, Model model) {
        Service service = serviceService.getServiceById(serviceId);
        model.addAttribute("service", service);
        return "/service/updateService";
    }

    @PostMapping("/update")
    public String updateServiceDetails(@ModelAttribute("service") Service service){
        serviceMapper.updateById(service);
        return "redirect:/admin/service/list";
    }

    @RequestMapping("/add")
    public String showAddServicePage(Model model){
        model.addAttribute("service", new Service());
        return "/service/addService";
    }

    @PostMapping("/insert")
    public String insertService(@ModelAttribute("service") Service service){
        serviceService.insert(service);
        return "redirect:/admin/service/list";
    }
//    @GetMapping("/recommended")
//    public String showRecommendedServices(Model model) {
//        List<Service> recommendedServices = serviceService.getRandomAllowedServices();
//        model.addAttribute("recommendedServices", recommendedServices);
//        // 打印获取到的随机服务数量
//        System.out.println("Number of random services: " + recommendedServices.size());
//        return "/customer/base";
//    }

}

