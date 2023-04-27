package com.cpt202.group7.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cpt202.group7.entity.*;
import com.cpt202.group7.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@org.springframework.stereotype.Service
public class AdminService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private ServiceMapper serviceMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;


    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        LocalDate today = LocalDate.now();
        LocalDateTime startOfToday = today.atStartOfDay();
        LocalDateTime endOfToday = today.plusDays(1).atStartOfDay();

        // 1. 计算今天的订单总数
        QueryWrapper<Order> todayOrderQuery = new QueryWrapper<>();
        todayOrderQuery.between("createTime", startOfToday, endOfToday);
        int orderCount = orderMapper.selectCount(todayOrderQuery).intValue();
        stats.put("orderCount", orderCount);

        // 2. 计算今天的预约总数
        List<Order> todayOrders = orderMapper.selectList(todayOrderQuery);
        int appointmentCount = todayOrders.stream().mapToInt(order -> appointmentMapper.selectCount(new QueryWrapper<Appointment>().eq("orderId", order.getOrderId())).intValue()).sum();
        stats.put("appointmentCount", appointmentCount);

        // 3. 计算今天的总收入
        double totalIncome = todayOrders.stream().mapToDouble(Order::getTotalPrice).sum();
        stats.put("totalIncome", totalIncome);

        // 4. 计算今天的评论平均星级
        QueryWrapper<Comment> commentQuery = new QueryWrapper<>();
        commentQuery.between("time", startOfToday, endOfToday);
        List<Comment> comments = commentMapper.selectList(commentQuery);
        double averageStarLevel = comments.stream().mapToInt(Comment::getStarLevel).average().orElse(0);
        stats.put("averageStarLevel", averageStarLevel);

        // 5. 计算最近七天的各个服务类型的总数统计
        LocalDateTime startOfLastSevenDays = today.minusDays(6).atStartOfDay();
        QueryWrapper<Order> lastSevenDaysOrderQuery = new QueryWrapper<>();
        lastSevenDaysOrderQuery.between("createTime", startOfLastSevenDays, endOfToday);
        List<Order> lastSevenDaysOrders = orderMapper.selectList(lastSevenDaysOrderQuery);

        Map<Integer, Long> serviceTypeCounts = new HashMap<>();
        for (Order order : lastSevenDaysOrders) {
            List<Appointment> appointments = appointmentMapper.selectList(new QueryWrapper<Appointment>().eq("orderId", order.getOrderId()));
            for (Appointment appointment : appointments) {
                serviceTypeCounts.put(appointment.getServiceId(), serviceTypeCounts.getOrDefault(appointment.getServiceId(), 0L) + 1);
            }
        }

        Map<String, Long> serviceTypeCountsWithName = new HashMap<>();
        for (Map.Entry<Integer, Long> entry : serviceTypeCounts.entrySet()) {
            Service service = serviceMapper.selectById(entry.getKey());
            serviceTypeCountsWithName.put(service.getName(), entry.getValue());
        }
        stats.put("serviceTypeCounts", serviceTypeCountsWithName);

        return stats;
    }


    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }




}
