package com.cpt202.group7.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PhotoMapper {
    @Update("UPDATE `cpt202-group7`.`groomer` SET `photo` = #{photoPath} WHERE `groomerId` = {groomerId}")
    void updateGroomerPhoto(String photoPath,Integer groomerId);



    @Update("UPDATE `cpt202-group7`.`pet_type` SET `iconURL` = #{photoPath} WHERE `petTypeId` = {petTypeId}")
    void updatePetTypePhoto(String photoPath,Integer petTypeId);


    @Update("UPDATE `cpt202-group7`.`service` SET `serviceURL` = #{photoPath} WHERE `serviceId` = {serviceId}")
    void updateServicePhoto(String photoPath,Integer ServiceId);



    @Update("UPDATE `cpt202-group7`.`user` SET `photo` = #{photoPath} WHERE `userId` = {userId}")
    void updateUserPhoto(String photoPath,Integer userId);
}
