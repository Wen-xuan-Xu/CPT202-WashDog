//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cpt202.group7.controller;

import cn.hutool.crypto.digest.mac.MacEngine;
import com.cpt202.group7.entity.Comment;
import com.cpt202.group7.entity.Groomer;
import com.cpt202.group7.entity.User;
import com.cpt202.group7.service.AdminService;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import com.cpt202.group7.service.GroomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/admin"})
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private GroomerService groomerService;

    @GetMapping({"/userManagement"})
    public String userManagement(Model model) {
        List<User> users = this.adminService.getAllUsers();
        model.addAttribute("users", users);
        return "userManagement";
    }

    @GetMapping("profitReport")
    public String getProfitReport(@RequestParam("startDate") String startDateString, @RequestParam("endDate") String endDateString, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);

        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.plusDays(1).atStartOfDay();

        Timestamp startTime = Timestamp.valueOf(startOfDay);
        Timestamp endTime = Timestamp.valueOf(endOfDay);

        model.addAllAttributes(this.adminService.calculateProfits(startTime, endTime));

        return "admin/profitReport";
    }

    @GetMapping("/groomerManagement")
    public String showGroomerManagement(Model model) {
        List<Groomer> groomers = groomerService.getGroomerList();
        model.addAttribute("groomers", groomers);
        return "groomerManagement";
    }

    @GetMapping("/{groomerId}/edit")
    public String showEditGroomerForm(@PathVariable("groomerId") Integer groomerId, Model model) {
        Groomer groomer = groomerService.getGroomer(groomerId);
        model.addAttribute("groomer", groomer);
        return "editGroomer";
    }

    @PostMapping("/{groomerId}/update")
    public String updateGroomer(@ModelAttribute Groomer groomer, RedirectAttributes redirectAttributes) {
        groomerService.updateGroomer(groomer);
        redirectAttributes.addFlashAttribute("message", "Groomer updated successfully!");
        return "redirect:/admin/groomerManagement";
    }


    @PostMapping("/groomerManagement/delete/{groomerId}")
    public String deleteGroomer(@PathVariable("groomerId") Integer groomerId, RedirectAttributes redirectAttributes) {
        groomerService.deleteGroomer(groomerId);
        redirectAttributes.addFlashAttribute("message", "Groomer deleted successfully!");
        return "redirect:/admin/groomerManagement";
    }


    @GetMapping("/userEvaluation")
    public List<Comment> getAllComments() {
        return adminService.getAllComments();
    }

    @PostMapping("/userEvaluation/{id}")
    public boolean deleteComment(@PathVariable Integer id) {
        return adminService.deleteComment(id);
    }

    @GetMapping("/reservationStatus")
    public String getReservationStatus(Model model) {
        List<Map<String, Object>> data = adminService.getReservationStatus();
        model.addAttribute("data", data);
        return "reservationStatus";
    }

    @PostMapping("/reservationStatus/{id}")
    public boolean deleteReservation(@PathVariable Integer id) {
        return adminService.deleteReservation(id);
    }

    @GetMapping("/addGroomer")
    public String addGroomer(Model model) {
        Groomer groomer = new Groomer();
        model.addAttribute("groomer", groomer);
        return "editGroomer";
    }

    @PostMapping("/addGroomer")
    public String addGroomerSubmit(@ModelAttribute Groomer groomer) {
        adminService.addGroomer(groomer);
        return "redirect:/groomerManagement";
    }
















}
