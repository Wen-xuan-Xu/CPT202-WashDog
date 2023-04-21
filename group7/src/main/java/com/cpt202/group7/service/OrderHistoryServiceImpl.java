package com.cpt202.group7.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpt202.group7.entity.*;
import com.cpt202.group7.mapper.*;
import com.cpt202.group7.service.Interface.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private ServiceMapper serviceMapper;
    @Autowired
    private GroomerMapper groomerMapper;
    @Autowired
    private PetMapper petMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private pet_typeMapper petTypeMapper;


    @Override
    public List<OrderHistoryDTO> findAllOrderHistoryByUserId(Integer userId) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("userId", userId);
        orderQueryWrapper.orderByDesc("createTime");
        List<Order> orders = orderMapper.selectList(orderQueryWrapper);

        return orders.stream().map(order -> {
            QueryWrapper<Appointment> appointmentQueryWrapper = new QueryWrapper<>();
            appointmentQueryWrapper.eq("orderId", order.getOrderId());
            return getOrderHistoryDTO(order, appointmentQueryWrapper);
        }).collect(Collectors.toList());
    }

    @Override
    public Page<OrderHistoryDTO> findAllOrderHistoryByUserIdWithPagination(Integer userId, Integer pageNo, Integer pageSize) {
        Page<Order> orderPage = new Page<>(pageNo, pageSize);
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("userId", userId);
        orderQueryWrapper.orderByDesc("createTime");
        Page<Order> paginatedOrders = orderMapper.selectPage(orderPage, orderQueryWrapper);

        List<OrderHistoryDTO> orderHistoryList = paginatedOrders.getRecords().stream().map(
                order -> {
                    QueryWrapper<Appointment> appointmentQueryWrapper = new QueryWrapper<>();
                    appointmentQueryWrapper.eq("orderId", order.getOrderId());
                    return getOrderHistoryDTO(order, appointmentQueryWrapper);
                }
        ).collect(Collectors.toList());
        Page<OrderHistoryDTO> orderHistoryPage = new Page<>(pageNo, pageSize);
        orderHistoryPage.setRecords(orderHistoryList);
        orderHistoryPage.setTotal(paginatedOrders.getTotal());

        return orderHistoryPage;
    }

    @Override
    public Page<OrderHistoryDTO> findOrderHistoryByUserIdWithPaginationAndStatusFilter(Integer userId, Integer pageNo, Integer pageSize, String statusFilter) {
        Page<Order> orderPage = new Page<>(pageNo, pageSize);
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("userId", userId);
        if ("finished".equalsIgnoreCase(statusFilter)) {
            orderQueryWrapper.eq("state", "Finished");
        } else if ("unfinished".equalsIgnoreCase(statusFilter)) {
            orderQueryWrapper.ne("state", "Finished");
        }

        orderQueryWrapper.orderByDesc("createTime");
        Page<Order> paginatedOrders = orderMapper.selectPage(orderPage, orderQueryWrapper);

        List<OrderHistoryDTO> orderHistoryList = paginatedOrders.getRecords().stream().map(
                order -> {
                    QueryWrapper<Appointment> appointmentQueryWrapper = new QueryWrapper<>();
                    appointmentQueryWrapper.eq("orderId", order.getOrderId());
                    System.out.println("dingdanbianhao"+order.getOrderId());
                    return getOrderHistoryDTO(order, appointmentQueryWrapper);
                }
        ).collect(Collectors.toList());

        Page<OrderHistoryDTO> orderHistoryPage = new Page<>(pageNo, pageSize);
        orderHistoryPage.setRecords(orderHistoryList);
        orderHistoryPage.setTotal(paginatedOrders.getTotal());

        return orderHistoryPage;
    }

    @Override
    public Map<String, Object> findOrderDetailByOrderId(Integer orderId) {
        Order order= orderMapper.selectById(orderId);
        Pet pet=petMapper.selectById(order.getPetId());
        pet_type type=petTypeMapper.selectById(pet.getPetTypeId());
        User user=userMapper.selectById(order.getUserId());

        QueryWrapper<Appointment> appointmentQueryWrapper = new QueryWrapper<>();
        appointmentQueryWrapper.eq("orderId", orderId);
        List<Appointment> appointments = appointmentMapper.selectList(appointmentQueryWrapper);

        List<Map<String,Object>> appointmentDetails=appointments.stream().map(appointment -> {
            Service service=serviceMapper.getService(appointment.getServiceId());
            Groomer groomer=groomerMapper.selectById(appointment.getGroomerId());
            Map<String,Object> appointmentDetail=new HashMap<>();
            appointmentDetail.put("serviceName",service.getName());
            appointmentDetail.put("servicePrice",service.getPrice());
            appointmentDetail.put("groomerName",groomer.getName());
            return appointmentDetail;
        }).collect(Collectors.toList());

        Map<String,Object> orderDetail=new HashMap<>();
        orderDetail.put("order",order);
        orderDetail.put("pet",pet);
        orderDetail.put("type",type);
        orderDetail.put("user",user);
        orderDetail.put("appointments",appointmentDetails);

        System.out.println("detail"+orderDetail);

        return orderDetail;

    }

    private OrderHistoryDTO getOrderHistoryDTO(Order order, QueryWrapper<Appointment> appointmentQueryWrapper) {
        List<Appointment> appointments = appointmentMapper.selectList(appointmentQueryWrapper);
        Appointment firstAppointment = appointments.get(0);
        Groomer firstGroomer = groomerMapper.selectById(firstAppointment.getGroomerId());

        String groomerName = firstGroomer.getName();
        String groomerPhoto = firstGroomer.getPhoto();

        if (appointments.size() > 1) {
            long distinctGroomers = appointments.stream()
                    .map(Appointment::getGroomerId)
                    .distinct()
                    .count();
            if (distinctGroomers > 1) {
                groomerName = firstGroomer.getName() + " \n(and others)";
            }
        }
        List<Service> services = appointments.stream()
                .map(appointment -> serviceMapper.getService(appointment.getServiceId()))
                .collect(Collectors.toList());

        String servicesSummary = services.get(0).getName();
        if (services.size() > 1) {
            servicesSummary += " + " + (services.size() - 1) + "\n more";
        }

        return new OrderHistoryDTO(
                order.getOrderId(),
                servicesSummary,
                groomerName,
                groomerPhoto,
                order.getCreateTime(),
                order.getState(),
                order.getTotalPrice()
        );
    }






}
