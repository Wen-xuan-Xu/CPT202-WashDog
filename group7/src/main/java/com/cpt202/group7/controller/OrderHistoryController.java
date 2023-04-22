package com.cpt202.group7.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpt202.group7.entity.OrderHistoryDTO;
import com.cpt202.group7.service.Interface.OrderHistoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class OrderHistoryController {
    @Autowired
    private OrderHistoryService orderHistoryService;




    @GetMapping("/{userId}/orderHistory")
    public String orderHistory(@PathVariable("userId") Integer userId,
                               @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                               @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                               @RequestParam(value = "statusFilter", defaultValue = "all") String statusFilter,
                               Model model,
                               HttpSession session){
        Page<OrderHistoryDTO> orderHistoryPage = orderHistoryService.findOrderHistoryByUserIdWithPaginationAndStatusFilter(userId, pageNo, pageSize, statusFilter);
//        System.out.println(orderHistoryPage.getRecords());
//        System.out.println(orderHistoryPage.getPages());//一共有多少页
//        System.out.println(orderHistoryPage.getTotal());//一共有多少条数据
//        System.out.println(orderHistoryPage.getCurrent());//当前页码值
//        System.out.println(orderHistoryPage.getSize());//每页显示数

        model.addAttribute("orderHistoryPage", orderHistoryPage.getRecords());
        model.addAttribute("userID",session.getAttribute("userid"));
        model.addAttribute("statusFilter", statusFilter);
        model.addAttribute("totalPageNumber", orderHistoryPage.getPages());
        model.addAttribute("currentPageNumber", orderHistoryPage.getCurrent());
        model.addAttribute("hasPrevious",orderHistoryPage.hasPrevious());
        model.addAttribute("hasNext",orderHistoryPage.hasNext());

        return "orderHistory";
    }

    @GetMapping("/{userId}/orderHistory/{orderId}")
    public String showOrderDetail(@PathVariable("orderId") String orderId, Model model) {
        Map<String, Object> orderDetail = orderHistoryService.findOrderDetailByOrderId(orderId);
        model.addAllAttributes(orderDetail);
        return "orderDetail";
    }
    @PostMapping("/{userId}/orderHistory/{orderId}/comment")
    public String submitComment(@PathVariable("orderId") Integer orderId,
                                @PathVariable("userId") Integer userId,
                                @RequestParam("starLevel") Integer starLevel,
                                @RequestParam("content") String content) {
        orderHistoryService.submitComment(userId, orderId, starLevel, content);
        // 重定向回订单详细信息页面
        return "redirect:/customer/"+userId+"/orderHistory/"+orderId;
    }


    @PostMapping("/{userId}/orderHistory/{orderId}/cancel")
    public String cancelOrder(@PathVariable("userId") Integer userId,
                              @PathVariable("orderId") Integer orderId) {
        orderHistoryService.cancelOrder(orderId);
        return "redirect:/customer"+userId+"/orderHistory/"+orderId;
    }

}





