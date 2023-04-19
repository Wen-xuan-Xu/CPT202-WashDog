package com.cpt202.group7.controller;

import com.cpt202.group7.entity.*;
import com.cpt202.group7.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer/dashboard/book-service")
public class BookServiceController {
    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private GroomerService groomerService;


    private List<LocalDate> getDateList() {
        List<LocalDate> next14Days = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (int i = 0; i < 14; i++) {
            LocalDate date = today.plusDays(i);
            next14Days.add(date);
        }
        return next14Days;
    }

    // Base Content
    @GetMapping("")
    public String showBase(Model model) {
        // Select Pet 1st
        List<Pet> petList = petService.getPetList();
        model.addAttribute("petList", petList);

        List<LocalDate> next14Days = getDateList();
        model.addAttribute("dates", next14Days);

        model.addAttribute("userPhoto", userService.getCurrentUserPhoto());
        return "/customer/bookService/base";
    }

    // Available Service Base on Type
    @GetMapping("/appointment")
    public String getAppointment(
            @RequestParam("petTypeId") Integer petTypeId,
            @RequestParam("date") String date,
            @RequestParam("time") String time,
            Model model) {

        List<Service> servicesList = serviceService.getServicesByPetTypeID(petTypeId);
        model.addAttribute("servicesList", servicesList);

        Timestamp passInStartTime = Timestamp.valueOf(date + " " + time + ":00");
        List<Groomer> groomersList = groomerService.getGroomerListByTheDate(passInStartTime, petTypeId);
        model.addAttribute("groomersList", groomersList);
        return "customer/bookService/appointment :: appointmentList";
    }


    @PostMapping("/submit")
    public String generateOrder(@ModelAttribute("petInfo") List<String[]> petInfo) {
        for (var pet : petInfo) {
            System.out.println(Arrays.toString(pet));
        }
        System.out.println("SBSBSBSS");
        return "/";
    }


}
