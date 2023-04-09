package com.cpt202.group7.entity;

import com.cpt202.group7.enumerator.AppointmentState;
import com.cpt202.group7.enumerator.PayMethod;
import lombok.Data;

import java.sql.Timestamp;
@Data
public class Appointment {
    private User user;
    private Pet pet;
    private Service service;
    private Groomer groomer;

    private Timestamp createTime;
    private Timestamp appointmentTime;

    private Double beforeDiscount;
    private Double discountCoefficient;
    private Double afterDiscount;

    private PayMethod payMethod;
    private AppointmentState appointmentState;


}
