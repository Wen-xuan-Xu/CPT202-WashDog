package com.cpt202.group7.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cpt202.group7.entity.Appointment;
import com.cpt202.group7.entity.DTO.OrderHistoryDTO;
import com.cpt202.group7.entity.Order;
import com.cpt202.group7.service.user.OrderHistoryService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/customer")
@Controller
public class OrderHistoryController {

    private OrderHistoryService orderHistoryService;

    @GetMapping("/{userId}/order-history")
    public String getOrderHistoryByUserId(@PathVariable("userId") Integer userId,Model model) {
        List<OrderHistoryDTO>orderHistoryDTOList=orderHistoryService.getOrderHistoryList(userId);
        model.addAttribute("orderHistoryList",orderHistoryDTOList);
        return "orderhistory";
    }

}
