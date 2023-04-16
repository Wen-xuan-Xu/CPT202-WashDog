package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.Appointment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AppointmentMapper {

    @Select("SELECT * FROM appointment WHERE groomerId = #{groomerId}")
    List<Appointment> getAppointmentListByGroomerId(Integer groomerId);
}
