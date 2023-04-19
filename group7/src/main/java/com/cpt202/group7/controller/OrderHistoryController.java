package com.cpt202.group7.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpt202.group7.entity.OrderHistoryDTO;
import com.cpt202.group7.service.Interface.OrderHistoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


}
