package com.cpt202.group7.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.cpt202.group7.entity.Groomer;
import com.cpt202.group7.entity.Pet;
import org.apache.ibatis.annotations.*;

import java.awt.*;
import java.util.List;


@Mapper
public interface GroomerMapper extends BaseMapper<Groomer> {
    @Select("select * FROM groomer")
    List<Groomer> getGroomerList();

    @Select("SELECT * FROM groomer WHERE groomerId = #{groomerId}")
    Groomer getGroomer(Integer groomerId);

    @Update("UPDATE pet SET name = #{name}, gender = #{gender}, age = #{age}, groomerStarLevelPriceCoefficientId = #{groomerStarLevelPriceCoefficientId}, selfIntroduction = #{selfIntroduction}, workStartTime = #{workStartTime},workEndTime = #{workEndTime} ,isWorking = #{isWorking}WHERE groomerId = #{groomerId}")
    void updateGroomer(Groomer groomer,Integer groomerId);

    @Insert("INSERT INTO `cpt202-group7`.`groomer` (`name`, `gender`, `age`, `groomerStarLevelPriceCoefficientId`, `selfIntroduction`, `workStartTime`, `workEndTime`,`isWorking`) VALUES (#{name}, #{gender}, #{age}, #{groomerStarLevelPriceCoefficientId}, #{selfIntroduction}, #{workStartTime}, #{workEndTime},#{isWorking})")
    void insertGroomer(Groomer groomer);

    @Delete("DELETE FROM `cpt202-group7`.`groomer` WHERE `groomerId` = #{groomerId}")
    void deleteGroomer(Integer groomerId);

    @Select("SELECT * FROM groomer WHERE groomerId IN (SELECT groomerId FROM groomer_service WHERE serviceID = #{serviceID}) ")
    List<Groomer> getGroomersByServiceID(Integer serviceID);
}
