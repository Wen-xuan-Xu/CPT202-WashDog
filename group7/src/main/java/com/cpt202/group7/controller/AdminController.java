//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cpt202.group7.controller;

import com.cpt202.group7.entity.User;
import com.cpt202.group7.service.AdminService;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/admin"})
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping({"/userManagement"})
    public String userManagement(Model model) {
        List<User> users = this.adminService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/userManagement";
    }

    @GetMapping({"profitReport"})
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


}
