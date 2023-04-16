package com.cpt202.group7.controller;

import com.cpt202.group7.service.Interface.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class OrderHistoryController {
    @Autowired
    private OrderHistoryService orderHistoryService;

    @GetMapping("/{userId}/orderHistory")
    public String orderHistory(@PathVariable("userId")Integer userId, Model model) {
        model.addAttribute("orderHistory", orderHistoryService.findAllOrderHistoryByUserId(userId));
        return "orderHistory";
    }
}
