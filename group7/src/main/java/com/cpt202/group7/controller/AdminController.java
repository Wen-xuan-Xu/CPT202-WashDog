//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cpt202.group7.controller;

//import cn.hutool.crypto.digest.mac.MacEngine;
import com.cpt202.group7.entity.Groomer;
import com.cpt202.group7.entity.User;
import com.cpt202.group7.mapper.GroomerMapper;
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
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private GroomerService groomerService;
    @Autowired
    private GroomerMapper groomerMapper;

    @GetMapping("/userManagement")
    public String userManagement(Model model) {
        List<User> users = this.adminService.getAllUsers();
        model.addAttribute("users", users);
        return "/userManagement";
    }

    @GetMapping("/profitReport")
    public String getProfitReport(@RequestParam("startDate") String startDateString, @RequestParam("endDate") String endDateString, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);

        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.plusDays(1).atStartOfDay();

        Timestamp startTime = Timestamp.valueOf(startOfDay);
        Timestamp endTime = Timestamp.valueOf(endOfDay);

        model.addAllAttributes(this.adminService.calculateProfits(startTime, endTime));

        return "/admin/profitReport";
    }

    @GetMapping("/groomerManagement")
    public String showGroomerManagement(Model model) {
        List<Groomer> groomers = groomerService.getGroomerList();
        model.addAttribute("groomers", groomers);
        return "/admin/groomerManagement";
    }

    @GetMapping("/groomerManagement/{groomerId}/edit")
    public String showEditGroomerForm(@PathVariable("groomerId") Integer groomerId, Model model) {
        Groomer groomer = groomerService.getGroomer(groomerId);
        model.addAttribute("groomer", groomer);
        return "editGroomer";
    }

    @PostMapping("/groomerManagement/{groomerId}/update")
    public String updateGroomer(@ModelAttribute Groomer groomer, RedirectAttributes redirectAttributes) {
        groomerMapper.updateById(groomer);
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
    public String getAllComments(Model model) {
        model.addAttribute(adminService.getAllComments());
        return "userEvaluation";
    }

    @PostMapping("/userEvaluation/{id}")
    public String deleteComment(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        boolean success = adminService.deleteComment(id);
        if (success) {
            redirectAttributes.addFlashAttribute("successMessage", "评论删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "评论删除失败，请重试。");
        }
        return "redirect:/admin/userEvaluation";
    }

    @GetMapping("/reservationStatus")
    public String getReservationStatus(Model model) {
        List<Map<String, Object>> data = adminService.getReservationStatus();
        model.addAttribute("data", data);
        return "reservationStatus";
    }

    @PostMapping("/reservationStatus/{id}")
    public String deleteReservation(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            boolean deleted = adminService.deleteReservation(id);
            if (deleted) {
                redirectAttributes.addFlashAttribute("successMessage", "预约删除成功！");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "预约删除失败！");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "预约删除出现错误：" + e.getMessage());
        }
        return "redirect:/admin/reservationStatus";
    }


    @GetMapping("/addGroomer")
    public String addGroomer(Model model) {
        Groomer groomer = new Groomer();
        groomer.setIsWorking(true); // 设置默认值为 true
        model.addAttribute("groomer", groomer);
        return "/admin/addGroomer";
    }

    @PostMapping("/addGroomer")
    public String addGroomerSubmit(@ModelAttribute Groomer groomer) {
        adminService.addGroomer(groomer);
        return "redirect:/admin/groomerManagement";
    }
















}
