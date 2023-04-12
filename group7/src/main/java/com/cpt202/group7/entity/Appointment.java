package com.cpt202.group7.entity;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Appointment {
    private Integer appointmentId;

    private Timestamp startTime;
    private Timestamp finishTime;

    private Integer serviceId;
    private Integer groomerId;
    private Integer orderId;
}
