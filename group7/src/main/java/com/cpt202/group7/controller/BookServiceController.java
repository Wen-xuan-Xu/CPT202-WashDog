package com.cpt202.group7.controller;

import com.cpt202.group7.entity.Appointment;
import com.cpt202.group7.entity.Groomer;
import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.entity.Service;
import com.cpt202.group7.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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

    @Autowired
    private AppointmentService appointmentService;

    // Base Content
    @GetMapping("")
    public String showBase(Model model, HttpSession session){
        List<String[]> petInfo = new ArrayList<>();
        // Get Pet List Of Current User
        List<Pet> petList = petService.getPetList();
        for(Pet pet:petList){
            petInfo.add(new String[]{pet.getPetTypeId().toString(), pet.getName(),pet.getType()});
            System.out.println(pet.getName());
        }
        model.addAttribute("petInfo",petInfo);

        model.addAttribute("userPhoto",userService.getCurrentUserPhoto());
        session.getAttribute("username");
        return "/customer/bookService/base";
    }

    // Available Service Base on Type
    @GetMapping("/service")
    public String getServices(@RequestParam("petTypeID") Integer petTypeID, Model model) {
        System.out.println("Select Pet Type ID: " + petTypeID);

        List<String[]> serviceInfo = new ArrayList<>();
        List<Service> servicesList = serviceService.getServicesByPetTypeID(petTypeID);

        if(!servicesList.isEmpty()){
           for (var service:servicesList){
               serviceInfo.add(new String[]{service.getServiceId().toString(), service.getName(), service.getPrice().toString()});
            }
        }

        if(serviceInfo.isEmpty()) {
            serviceInfo.add(new String[]{"","No services available",""});
        }

        model.addAttribute("serviceInfo", serviceInfo);
        return "customer/bookService/services :: servicesList";
    }

    // Available Groomers Base on Service
    @GetMapping("/groomer")
    public String getGroomers(@RequestParam("serviceTypeID") Integer serviceTypeID, Model model) {
        System.out.println("Select Service Type ID: " + serviceTypeID);

        List<String[]> groomerInfo = new ArrayList<>();
        List<Groomer> groomerList = groomerService.getGroomersByServiceID(serviceTypeID);

        if(!groomerList.isEmpty()){
            for (var groomer:groomerList){
                if(groomer.isWorking()){
                    groomerInfo.add(new String[]{groomer.getGroomerId().toString(), groomer.getName(), groomer.getGroomerStarLevelPriceCoefficientId().toString()});
                    System.out.println(groomer.getName());
                }
            }
        }

        if(groomerInfo.isEmpty()){
            groomerInfo.add(new String[]{"","No groomers available",""});
        }

        model.addAttribute("groomerInfo", groomerInfo);
        return "customer/bookService/groomers :: groomersList";
    }

    // Available Time List Base on Groomers
    @GetMapping("/time")
    public String getTimeList(@RequestParam("groomerTypeID") Integer groomerTypeID, Model model){

        System.out.println("Select Groomer Type ID: " + groomerTypeID);
        List<Appointment> appointmentList = appointmentService.getAppointmentListByGroomerId(groomerTypeID);

        // 30 min 间隔
        boolean[] timeSlots = new boolean[48];

        for(int i = 0; i< timeSlots.length; i++){
            timeSlots[i] = i >= 18 && i <= 34;
        }

        // Appointment is null or empty -> Can Be Booked
        if (appointmentList.isEmpty()){
            model.addAttribute("timeSlots", timeSlots);
        }else {

        }

        return "customer/bookService/timeSlots :: timesList";
    }

//    @PostMapping("/submit")
//    public String generateOrder(){
//
//    }

}
