package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.Appointment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@Rollback

class AppointmentMapperTest {
    @Autowired
    private AppointmentMapper appointmentMapper;

    @Test
    void getAppointmentListByGroomerId() {
        List<Appointment> appointmentTest = appointmentMapper.getAppointmentListByGroomerId(2);
        assertEquals(8,appointmentTest.size());
    }
}