package com.cpt202.group7.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpt202.group7.entity.Customer;
import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.entity.User;
import jakarta.servlet.http.PushBuilder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PetMapper {
//    @Select("select p.name, p.sex, p_type.type, weight FROM pet p JOIN pet_type p_type ON p.pet_type_id = p_type.pet_type_id")
//    List<Pet> ShowPet();
//
//    @Select("SELECT pet_type_id FROM pet_type WHERE type = #{type}")
//    public int findPetTypeID(String type);

    @Select("select * FROM pet WHERE userId = #{userId}")
    List<Pet> getPetList(Integer userId);

    @Select("SELECT * FROM pet WHERE petId = #{petId}")
    Pet getPet(Integer petId);

    @Select("select petTypeId from pet_type WHERE type = #{type}")
    Integer getTypeId(String type);
    @Select("select type FROM pet_type WHERE petTypeId = #{petTypeId}")
    String getType(Integer petTypeId);

    @Update("UPDATE pet SET name = #{name}, age = #{age}, sex = #{sex}, tips = #{tips}, petTypeId = #{petTypeId}, size = #{size} WHERE petId = #{petId}")
    void updatePet(Pet pet);
    @Insert("INSERT INTO `cpt202-group7`.`pet` (`userId`, `petTypeId`, `sex`, `size`, `age`, `name`, `tips`) VALUES (#{userId}, #{petTypeId}, #{sex}, #{size}, #{age}, #{name}, #{tips})")
    void insertPet(Pet pet);

    @Delete("DELETE FROM `cpt202-group7`.`pet` WHERE `petId` = #{petId}")
    void deletePet(Integer petId);
}
