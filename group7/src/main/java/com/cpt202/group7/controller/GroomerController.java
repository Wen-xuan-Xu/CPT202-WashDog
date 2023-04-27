package com.cpt202.group7.controller;

import com.cpt202.group7.entity.Groomer;
import com.cpt202.group7.service.GroomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/groomer")
public class GroomerController {
    @Autowired
    private GroomerService groomerService;

    @RequestMapping("/list")
    public String showGroomers(Model model)
    {
        List<Groomer> groomers = groomerService.getGroomerList();
        model.addAttribute("groomer",groomers);
        return "/groomer/GroomerList";
    }

    @PostMapping("/delete")
    public String deleteGroomer(@RequestParam Integer groomerId){
        groomerService.deleteGroomer(groomerId);
        return "redirect:/admin/groomer/list";
    }

    @GetMapping("/detail")
    public String showgroomerDetails(Model model, Integer groomerId){
        Groomer groomer = groomerService.getGroomer(groomerId);
        model.addAttribute("groomer",groomer);
        model.addAttribute("groomerid",groomerId);
        return "/groomer/updateGroomer";
    }

    @PostMapping("/update")
    public String updateGroomerDetails(@ModelAttribute("groomer") Groomer groomer){
        groomerService.updateGroomer(groomer);
        return "redirect:/admin/groomer/list";
    }

    @RequestMapping("/add")
    public String showAddGroomerPage(Model model){
        model.addAttribute("groomer", new Groomer());
        return "/groomer/addGroomer";
    }

    @PostMapping("/insert")
    public String insertGroomer(@ModelAttribute("groomer") Groomer groomer){
        groomerService.insert(groomer);
        return "redirect:/admin/groomer/list";
    }



}
