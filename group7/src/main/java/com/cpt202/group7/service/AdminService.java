package com.cpt202.group7.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cpt202.group7.entity.*;
import com.cpt202.group7.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private GroomerMapper groomerMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private pet_typeMapper pet_typeMapper;

    @Autowired
    private PetMapper petMapper;


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

    public Map<String, Object> calculateProfits(Timestamp startTime, Timestamp endTime) {
        Map<String, Object> result = new HashMap<>();
        List<Order> orders = getOrdersBetweenDates(startTime, endTime);

        int orderCount = orders.size();
        double totalOrderIncome = 0.0;

        Map<String, Double> serviceIncomeMap = new HashMap<>();
        Map<String, Double> groomerIncomeMap = new HashMap<>();

        for (Order order : orders) {
            totalOrderIncome += order.getTotalPrice();

            List<Appointment> appointments = appointmentMapper.selectList(new QueryWrapper<Appointment>().lambda().eq(Appointment::getOrderId, order.getOrderId()));
            for (Appointment appointment : appointments) {
                Service service = serviceMapper.selectById(appointment.getServiceId());
                if (service != null) {
                    serviceIncomeMap.put(service.getName(), serviceIncomeMap.getOrDefault(service.getName(), 0.0) + service.getPrice());
                }

                Groomer groomer = groomerMapper.selectById(appointment.getGroomerId());
                if (groomer != null) {
                    groomerIncomeMap.put(groomer.getName(), groomerIncomeMap.getOrDefault(groomer.getName(), 0.0) + service.getPrice());
                }
            }
        }

        result.put("orderCount", orderCount);
        result.put("totalOrderIncome", totalOrderIncome);
        result.put("serviceIncomeMap", serviceIncomeMap);
        result.put("groomerIncomeMap", groomerIncomeMap);

        return result;
    }

    private List<Order> getOrdersBetweenDates(Timestamp startTime, Timestamp endTime) {
        return orderMapper.selectList(new QueryWrapper<Order>().lambda().between(Order::getCreateTime, startTime, endTime));
    }

    public List<Comment> getAllComments() {
        List<Comment> comments = commentMapper.selectList(null);
        for (Comment comment : comments) {
            User user = userMapper.selectById(comment.getUserId());
            comment.setUsername(user.getNickname());
            List<Appointment> appointments = appointmentMapper.selectList(new QueryWrapper<Appointment>().eq("orderId", comment.getOrderId()));
            for (Appointment appointment : appointments) {
                Groomer groomer = groomerMapper.selectById(appointment.getGroomerId());
                appointment.setGroomerName(groomer.getName());
                Service service = serviceMapper.selectById(appointment.getServiceId());
                appointment.setServiceName(service.getName());
            }
            comment.setAppointments(appointments);
        }
        return comments;
    }

    public boolean deleteComment(Integer id) {
        int rows = commentMapper.deleteById(id);
        return rows == 1;
    }

    public List<Map<String, Object>> getReservationStatus() {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Order> orders = orderMapper.selectList(null);
        for (Order order : orders) {
            User user = userMapper.selectById(order.getUserId());
            Pet pet = petMapper.selectById(order.getPetId());
            pet_type petType = pet_typeMapper.selectById(pet.getPetTypeId());

            List<Appointment> appointments = appointmentMapper.selectList(new QueryWrapper<Appointment>().eq("orderId", order.getOrderId()));
            for (Appointment appointment : appointments) {
                Groomer groomer = groomerMapper.selectById(appointment.getGroomerId());
                appointment.setGroomerName(groomer.getName());
                Service service = serviceMapper.selectById(appointment.getServiceId());
                appointment.setServiceName(service.getName());

                Map<String, Object> map = new HashMap<>();
                map.put("Time", order.getCreateTime());
                map.put("customerName", user.getNickname());
                map.put("customerPhoneNumber", user.getPhone());
                map.put("appointmentId", appointment.getAppointmentId());
                map.put("serviceName", appointment.getServiceName());
                map.put("groomerName", appointment.getGroomerName());
                map.put("petType", petType.getType());
                map.put("status", order.getState());
                result.add(map);
            }
        }

        return result;
    }

    public boolean deleteReservation(Integer id) {
        int rows = appointmentMapper.deleteById(id);
        return rows == 1;
    }

    public boolean addGroomer(Groomer groomer) {
        int rows = groomerMapper.insert(groomer);
        return rows > 0;
    }


}
