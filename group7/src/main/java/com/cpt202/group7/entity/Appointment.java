package com.cpt202.group7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    private Integer appointmentId;

    private Timestamp startTime;
    private Timestamp finishTime;

    private Integer serviceId;
    private Integer groomerId;
    private Integer orderId;
}
