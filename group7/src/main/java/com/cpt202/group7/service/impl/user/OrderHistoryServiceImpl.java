package com.cpt202.group7.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cpt202.group7.entity.Appointment;
import com.cpt202.group7.entity.DTO.OrderHistoryDTO;
import com.cpt202.group7.entity.Groomer;
import com.cpt202.group7.entity.Order;
import com.cpt202.group7.mapper.AppointMapper;
import com.cpt202.group7.mapper.GroomerMapper;
import com.cpt202.group7.mapper.OrderMapper;
import com.cpt202.group7.mapper.ServiceMapper;
import com.cpt202.group7.service.user.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import com.cpt202.group7.entity.Service;

import javax.management.Query;
import java.util.List;
import java.util.stream.Collectors;

public class OrderHistoryServiceImpl implements OrderHistoryService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AppointMapper appointmentMapper;
    @Autowired
    private ServiceMapper serviceMapper;
    @Autowired
    private GroomerMapper groomerMapper;

    @Override
    public List<OrderHistoryDTO> getOrderHistoryList(Integer userId) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("userId", userId);
        orderQueryWrapper.orderByDesc("createTime");
        List<Order> orders = orderMapper.selectList(orderQueryWrapper);

        return orders.stream().map(order -> {
            QueryWrapper<Appointment> appointmentQueryWrapper = new QueryWrapper<>();
            appointmentQueryWrapper.eq("orderId", order.getOrderId());
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
                    .map(appointment -> serviceMapper.selectById(appointment.getServiceId()))
                    .collect(Collectors.toList());

            String servicesSummary = services.get(0).getName();
            if (services.size() > 1) {
                servicesSummary += " + " + (services.size() - 1) + "\n more";
            }

            return new OrderHistoryDTO(
                    servicesSummary,
                    groomerName,
                    groomerPhoto,
                    order.getCreateTime(),
                    order.getStatus(),
                    order.getTotalPrice()
            );
        }).collect(Collectors.toList());
    }




}
