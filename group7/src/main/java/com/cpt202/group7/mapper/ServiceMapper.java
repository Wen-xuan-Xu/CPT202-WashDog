package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.Service;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ServiceMapper{

    @Select("SELECT * FROM service WHERE serviceId IN (SELECT serviceId FROM pet_service WHERE petTypeId = #{petTypeID}) ")
    List<Service> getServicesByPetTypeID(Integer petTypeID);
}
