package com.cpt202.group7.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpt202.group7.entity.Customer;
import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.entity.User;
import jakarta.servlet.http.PushBuilder;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface PetMapper extends BaseMapper<Pet>{

    @Select("select * FROM pet WHERE userId = #{userId}")
    List<Pet> getPetList(Integer userId);

    @Select("SELECT * FROM pet WHERE petId = #{petId}")
    Pet getPet(Integer petId);

    @Select("select petTypeId from pet_type WHERE type = #{type}")
    Integer getTypeId(String type);
    @Select("select type FROM pet_type WHERE petTypeId = #{petTypeId}")
    String getType(Integer petTypeId);

    @Select("select iconURL FROM pet_type WHERE petTypeId = #{petTypeId}")
    String getIconURL(Integer petTypeId);

    @Update("UPDATE pet SET name = #{name}, age = #{age}, sex = #{sex}, tips = #{tips}, petTypeId = #{petTypeId}, size = #{size} WHERE petId = #{petId}")
    void updatePet(Pet pet);
    @Insert("INSERT INTO `cpt202-group7`.`pet` (`userId`, `petTypeId`, `sex`, `size`, `age`, `name`, `tips`) VALUES (#{userId}, #{petTypeId}, #{sex}, #{size}, #{age}, #{name}, #{tips})")
    void insertPet(Pet pet);

    @Delete("DELETE FROM `cpt202-group7`.`pet` WHERE `petId` = #{petId}")
    void deletePet(Integer petId);

    @Select("SELECT pet_type.type FROM pet_type")
    List<String>getAllPetType();
}
