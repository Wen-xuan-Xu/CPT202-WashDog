package com.cpt202.group7.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpt202.group7.entity.OrderHistoryDTO;
import com.cpt202.group7.service.Interface.OrderHistoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(userId);
        System.out.println(session.getAttribute("userid"));
        if (userId!=Integer.parseInt(session.getAttribute("userid").toString())) {
            return "404 Not Found";
        }

        Page<OrderHistoryDTO> orderHistoryPage = orderHistoryService.findOrderHistoryByUserIdWithPaginationAndStatusFilter(userId, pageNo, pageSize, statusFilter);

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

    @PostMapping("/orderHistory/comment")
    public String submitComment(@RequestParam("orderId") String orderId,
                                @RequestParam("userId") Integer userId,
                                @RequestParam("starLevel") Integer starLevel,
                                @RequestParam("content") String content) {
        orderHistoryService.submitComment(userId, orderId, starLevel, content);
        // 重定向回订单详细信息页面
        return "redirect:/customer/"+userId+"/orderHistory/"+orderId;
    }


    @PostMapping("/orderHistory/cancel")
    public String cancelOrder(@RequestParam("userId") Integer userId,
                              @RequestParam("orderId") String orderId) {
        orderHistoryService.cancelOrder(orderId);
        return "redirect:/customer/"+userId+"/orderHistory";
    }

}





