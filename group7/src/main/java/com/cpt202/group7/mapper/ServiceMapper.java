package com.cpt202.group7.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpt202.group7.entity.service;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ServiceMapper extends BaseMapper<service> {


    @Select("select * FROM service")
    List<service> getServiceList();

    @Select("SELECT * FROM service WHERE serviceId = #{serviceId}")
    service getService(Integer serviceId);

    @Update("UPDATE service SET name = #{name},price=#{price},duration=#{duration} WHERE serviceId = #{serviceId}")
    void updateService(service service, Integer serviceId);

    @Insert("INSERT INTO `cpt202-group7`.`service` (`name`, `price`, `duration`,`briefIntroduction`,`detailIntroduction`) VALUES (#{name}, #{price}, #{duration},#{briefIntroduction},#{detailIntroduction})")
    void insertService(service service);

    @Delete("DELETE FROM `cpt202-group7`.`service` WHERE `serviceId` = #{serviceId}")
    void deleteService(Integer serviceId);


    @Select("SELECT * FROM service WHERE serviceId IN (SELECT serviceId FROM pet_service WHERE petTypeId = #{petTypeID}) ")
    List<service> getServicesByPetTypeID(Integer petTypeID);
}
