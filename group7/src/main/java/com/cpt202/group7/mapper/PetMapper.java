package com.cpt202.group7.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpt202.group7.entity.Customer;
import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.entity.User;
import jakarta.servlet.http.PushBuilder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PetMapper {
//    @Select("select p.name, p.sex, p_type.type, weight FROM pet p JOIN pet_type p_type ON p.pet_type_id = p_type.pet_type_id")
//    List<Pet> ShowPet();
//
//    @Select("SELECT pet_type_id FROM pet_type WHERE type = #{type}")
//    public int findPetTypeID(String type);

//    @Insert("INSERT INTO Pet(user_id, pet_type_id,sex,weight,age,name, tips) VALUES (#{user_id}, #{pet_type_id}, #{sex}, #{weight},#{weight},#{age},#{name},#{tips}")
//    void insertPet(Pet pet);
    @Select("SELECT * FROM pet")
    List<Pet> ShowPet();


}
