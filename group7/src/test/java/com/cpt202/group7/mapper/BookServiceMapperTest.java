package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.Appointment;
import com.cpt202.group7.entity.Order;
import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.entity.User;
import com.cpt202.group7.utils.GenerateOrderUUID.UUIDUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@Rollback
class BookServiceMapperTest {

    @Autowired
    private BookServiceMapper bookServiceMapper;
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private  PetMapper petMapperl;
    @Autowired
    private UserMapper userMapper;

    @Test
    void getCurrentOrder() {
        Order order = bookServiceMapper.getCurrentOrder("056c4c0fc2574cd98fade40bf8f26aa3");
        assertNotNull(order);
        assertThat(order.getOrderId()).isEqualTo("056c4c0fc2574cd98fade40bf8f26aa3");
    }


    @Test
    void insertOrderandInsertappointment() {
        Order order = new Order();
        order.setOrderId("123456789");
        order.setUserId(50);
        order.setCreateTime(bookServiceMapper.getCurrentOrder("056c4c0fc2574cd98fade40bf8f26aa3").getCreateTime());
        order.setPetId(1);
        order.setTotalPrice(100.0);
        order.setState("UNPAID");
        order.setStartTime(bookServiceMapper.getCurrentOrder("056c4c0fc2574cd98fade40bf8f26aa3").getStartTime());
        order.setEndTime(bookServiceMapper.getCurrentOrder("056c4c0fc2574cd98fade40bf8f26aa3").getEndTime());
        bookServiceMapper.insertOrder(order);
        assertThat(bookServiceMapper.getCurrentOrder("123456789")).isEqualTo(order);
        System.out.println("pass test 1");
        Appointment appointment = new Appointment();
        appointment.setServiceId(1);
        appointment.setOrderId(order.getOrderId());
        appointment.setGroomerId(1);
        int before = appointmentMapper.getAppointmentListByGroomerId(1).size();
        bookServiceMapper.insertAppointment(appointment);
        int after = appointmentMapper.getAppointmentListByGroomerId(1).size();
        assertThat(before).isEqualTo(after-1);
        System.out.println("pass test 2");
    }

    @Test
    void insertAppointment() {
        Appointment appointment = new Appointment();
        appointment.setServiceId(1);
        appointment.setOrderId("056c4c0fc2574cd98fade40bf8f26aa3");
        appointment.setGroomerId(1);
        int before = appointmentMapper.getAppointmentListByGroomerId(1).size();
        bookServiceMapper.insertAppointment(appointment);
        int after = appointmentMapper.getAppointmentListByGroomerId(1).size();
        assertThat(before).isEqualTo(after-1);

    }

    @Test
    void updateOrderState() {
        Order order = bookServiceMapper.getCurrentOrder("056c4c0fc2574cd98fade40bf8f26aa3");
        order.setState("UNPAID");
        assertThat(order.getState()).isEqualTo("UNPAID");
    }


    @Test
    void getOrderPet() {
        Pet pet  = bookServiceMapper.getOrderPet(16);
        assertThat(pet).isEqualTo(petMapperl.getPet(16));
    }

    @Test
    void getOrderUser() {
        User user = bookServiceMapper.getOrderUser(50);
        assertThat(user).isEqualTo(userMapper.findByUsername("Q@Q"));
    }

    @Test
    void successPay() {
        //Order testOrder=bookServiceMapper.getCurrentOrder("64d80436ae9a4caab68e350368ab5c8b");
        bookServiceMapper.successPay("64d80436ae9a4caab68e350368ab5c8b");
        assertThat(bookServiceMapper.getCurrentOrder("64d80436ae9a4caab68e350368ab5c8b").getState()).isEqualTo("PAID");

    }
}