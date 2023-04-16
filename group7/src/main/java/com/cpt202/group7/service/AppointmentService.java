package com.cpt202.group7.service;

import com.cpt202.group7.entity.Appointment;
import com.cpt202.group7.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentMapper appointmentMapper;

    public List<Appointment> getAppointmentListByGroomerId(Integer groomerId){
        return appointmentMapper.getAppointmentListByGroomerId(groomerId);
    }
}
