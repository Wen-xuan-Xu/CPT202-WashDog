package com.cpt202.group7.service;

import com.cpt202.group7.entity.Appointment;
import com.cpt202.group7.entity.Order;
import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.mapper.AppointmentMapper;
import com.cpt202.group7.mapper.BookServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookServiceMapper bookServiceMapper;

    public void insertOrder(Order order){
        bookServiceMapper.insertOrder(order);
    }

    public void insertAppointment(Appointment appointment){
        bookServiceMapper.insertAppointment(appointment);
    }
}
