package com.cpt202.group7.controller;

import com.cpt202.group7.entity.Groomer;
import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.entity.Service;
import com.cpt202.group7.service.GroomerService;
import com.cpt202.group7.service.PetService;
import com.cpt202.group7.service.ServiceService;
import com.cpt202.group7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        return "/customer/bookService/appointment :: appointmentList";
    }

    @RequestMapping("/submit")
    public String generateOrder(@RequestParam("petTypeId") String petTypeId,
                                @RequestParam("date") String date,
                                @RequestParam("time") String time,

                                @RequestParam("services") List<String> services,
                                @RequestParam("groomers") List<String> groomers

    ) {
        System.out.println("Selected Pet Type ID" + petTypeId);
        System.out.println("Selected Date" + date);
        System.out.println("Selected Time" + time);

        System.out.println("Selected Service Order");
        for (var service : services) {
            System.out.println(service);
        }

        System.out.println("Selected Groomer Order");
        for (var groomer : groomers) {
            System.out.println(groomer);
        }
        return "/customer/bookService/base";
    }
}
