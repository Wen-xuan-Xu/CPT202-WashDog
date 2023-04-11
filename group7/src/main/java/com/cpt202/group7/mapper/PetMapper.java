package com.cpt202.group7.mapper;


import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PetMapper {
    @Select("select p.name, p.sex, p_type.type, weight FROM pet p JOIN pet_type p_type ON p.pet_type_id = p_type.pet_type_id")
    List<Pet> ShowPet();


    @Insert("INSERT INTO Pet(pet_id, name, breed,age,sex,weight,owner, specification) VALUES (#{pet_id}, #{name}, #{breed}, #{age}, #{sex},#{weight},#{owner},#{tips}")
    int insertpet(@Param("pet_id")Integer pet_id,
                  @Param("name")String name,
                  @Param("breed")String breed,
                  @Param("age")Integer age,
                  @Param("sex")String sex,
                  @Param("weight")Double weight,
                  @Param("owner") User owner,
                  @Param("tips")String tips);




}
