package com.cpt202.group7.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.cpt202.group7.entity.Groomer;
import com.cpt202.group7.entity.Service;

import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper

public interface ServiceMapper extends BaseMapper<Service> {

    @Select("select * FROM service")
    List<Service> getServiceList();

    @Select("SELECT * FROM service WHERE serviceId = #{serviceId}")
    Service getService(Integer serviceId);

    @Update("UPDATE service SET name = #{service.name},price=#{service.price},duration=#{service.duration},allowUpselling=#{service.allowUpselling},allowCross=#{service.allowCross} WHERE serviceId = #{serviceId}")
    void updateService(Service service,Integer serviceId);

    @Insert("INSERT INTO `cpt202-group7`.`service` (`name`, `price`, `duration`,`briefIntroduction`,`detailIntroduction`) VALUES (#{name}, #{price}, #{duration},#{briefIntroduction},#{detailIntroduction})")
    void insertService(Service service);


    @Delete("DELETE FROM `cpt202-group7`.`service` WHERE `serviceId` = #{serviceId}")
    void deleteService(Integer serviceId);

    @Select("SELECT * FROM service WHERE serviceId IN (SELECT serviceId FROM pet_service WHERE petTypeId = #{petTypeID}) ")
    List<Service> getServicesByPetTypeID(Integer petTypeID);
    @Select("SELECT * FROM service WHERE allowUpselling = 1 OR allowCross = 1 ORDER BY RAND() LIMIT 5")
    List<Service> getRandomAllowedServices();

}
