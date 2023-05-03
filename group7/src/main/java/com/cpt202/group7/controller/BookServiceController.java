package com.cpt202.group7.controller;

import com.cpt202.group7.entity.*;
import com.cpt202.group7.service.*;
import com.cpt202.group7.utils.GenerateOrderUUID.UUIDUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    private BookService bookService;





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
    public String showBase(
            Model model) {
        // Select Pet 1st
        List<Pet> petList = petService.getPetList();
        model.addAttribute("petList", petList);

        List<LocalDate> next14Days = getDateList();
        model.addAttribute("dates", next14Days);

        model.addAttribute("userPhoto", userService.getCurrentUserPhoto());
        model.addAttribute("userId", userService.getCurrentUserID());
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
        for (var s:servicesList){
            System.out.println(s.getPrice());
        }
        model.addAttribute("servicesList", servicesList);

        Timestamp passInStartTime = Timestamp.valueOf(date + " " + time + ":00");
        List<Groomer> groomersList = groomerService.getGroomerListByTheDate(passInStartTime, petTypeId);
        model.addAttribute("groomersList", groomersList);
        return "/customer/bookService/appointment";
    }

    @RequestMapping("/submit")
    public String generateOrder(@RequestParam("petId") String petId,
                                @RequestParam("date") String date,
                                @RequestParam("time") String time,

                                @RequestParam("services") List<String> services,
                                @RequestParam("groomers") List<String> groomers,
                                @RequestParam("totalPrice") String totalPrice,
                                @RequestParam("totalDuration") String totalDuration,
                                Model model
    ) {
        System.out.println("Selected Pet ID" + petId);
        System.out.println("Selected Date" + date);
        System.out.println("Selected Time" + time);

        System.out.println(totalPrice);
        System.out.println(totalDuration);

        String orderId = UUIDUtils.getId();

        Timestamp startTime = Timestamp.valueOf(date + " " + time + ":00");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.MINUTE, Integer.parseInt(totalDuration));
        Timestamp endTime = new Timestamp(calendar.getTimeInMillis());

        Order order = new Order();
        order.setOrderId(orderId);
        order.setUserId(userService.getCurrentUserID());
        order.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        order.setPetId(Integer.valueOf(petId));
        order.setTotalPrice(Double.valueOf(totalPrice));
        order.setState("UNPAID");
        order.setStartTime(startTime);
        order.setEndTime(endTime);
        bookService.insertOrder(order);


        List<Service> services1 = new ArrayList<>();
        List<Groomer> groomers1 = new ArrayList<>();

        for (int i = 0; i < services.size(); i++) {
            Appointment appointment = new Appointment();
            appointment.setServiceId(Integer.valueOf(services.get(i)));
            appointment.setGroomerId(Integer.valueOf(groomers.get(i)));

            services1.add(serviceService.getService(Integer.valueOf(services.get(i))));
            groomers1.add(groomerService.getGroomer(Integer.valueOf(groomers.get(i))));

            appointment.setOrderId(orderId);
            bookService.insertAppointment(appointment);
        }

        System.out.println("Selected Service Order");
        for (var service : services) {
            System.out.println(service);
        }

        System.out.println("Selected Groomer Order");
        for (var groomer : groomers) {
            System.out.println(groomer);
        }

        model.addAttribute("order", order);
        model.addAttribute("services", services1);
        model.addAttribute("groomers", groomers1);
        model.addAttribute("pet", bookService.getOrderPet(order.getPetId()));
        model.addAttribute("user", bookService.getOrderUser(order.getUserId()));
        return "redirect:/customer/"+ order.getUserId()+"/orderHistory/"+orderId;
//        return "redirect:/customer/" + order.getUserId() + "/orderHistory";
    }
    @RequestMapping("/pay")
    public String successPay(String orderId, HttpSession session){
        bookService.successPay(orderId);
        int userID=Integer.parseInt(session.getAttribute("userid").toString());
        return "redirect:/customer/"+userID+"/orderHistory";
    }

}
