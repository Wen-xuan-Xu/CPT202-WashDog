package com.cpt202.group7.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.entity.User;
import jakarta.servlet.http.PushBuilder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PetMapper {

    @Select("select * FROM pet WHERE userId = #{userId}")
    List<Pet> showpet(Integer userId);

    @Select("SELECT * FROM pet WHERE petId = #{petId}")
    Pet GetPet(Integer petId);

    @Select("select petTypeId from pet_type WHERE type = #{type}")
    Integer GetTypeId(String type);
    @Select("select type FROM pet_type WHERE petTypeId = #{petTypeId}")
    String GetType(Integer petTypeId);

    @Update("UPDATE pet SET name = #{name}, age = #{age}, sex = #{sex}, tips = #{tips}, petTypeId = #{petTypeId}, size = #{size} WHERE petId = #{petId}")
    void updatePet(Pet pet);
    @Insert("INSERT INTO `cpt202-group7`.`pet` (`userId`, `petTypeId`, `sex`, `size`, `age`, `name`, `tips`) VALUES (#{userId}, #{petTypeId}, #{sex}, #{size}, #{age}, #{name}, #{tips})")
    void InsertPet(Pet pet);

    @Delete("DELETE FROM `cpt202-group7`.`pet` WHERE `petId` = #{petId}")
    void DeletePet(Integer petId);
}
