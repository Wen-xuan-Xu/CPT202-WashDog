package com.cpt202.group7.entity;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Appointment {
    private Integer appointment_id;

    private Timestamp start_time;
    private Timestamp finish_time;

    private Integer service_id;
    private Integer groomer_id;
    private Integer order_id;
}
