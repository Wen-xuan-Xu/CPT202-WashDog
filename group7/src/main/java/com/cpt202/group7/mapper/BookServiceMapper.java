package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.Appointment;
import com.cpt202.group7.entity.Order;
import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BookServiceMapper {
    @Insert("INSERT INTO `cpt202-group7`.`order` (`orderId`, `userId`, `createTime`, `petId`, `totalPrice`, `state`, `startTime`,`endTime`) VALUES (#{orderId}, #{userId}, #{createTime}, #{petId}, #{totalPrice}, #{state}, #{startTime},#{endTime})")
    void insertOrder(Order order);


    @Insert("INSERT INTO `cpt202-group7`.`appointment` (`serviceId`, `groomerId`,`orderId`) VALUES (#{serviceId},#{groomerId},#{orderId})")
    void insertAppointment(Appointment appointment);


    @Update("UPDATE order SET name = #{name}, age = #{age}, sex = #{sex}, tips = #{tips}, petTypeId = #{petTypeId}, size = #{size} WHERE petId = #{petId}")
    void updateOrderState(Order order);



    @Select("SELECT * FROM `order` WHERE `order`.orderId = #{orderId}")
    Order getCurrentOrder(String orderId);

    @Select("SELECT * FROM pet WHERE pet.petId = #{petId}")
    Pet getOrderPet(Integer petId);

    @Select("SELECT `user`.*FROM`user`WHERE`user`.userId = #{userId}")
    User getOrderUser(Integer userId);

    @Update("UPDATE `cpt202-group7`.`order` SET `state` = 'PAID' WHERE `orderId` = #{orderId}")
    void successPay(String orderId);
}
