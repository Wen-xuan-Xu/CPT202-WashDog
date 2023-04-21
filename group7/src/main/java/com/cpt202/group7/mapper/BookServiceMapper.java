package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.Appointment;
import com.cpt202.group7.entity.Order;
import com.cpt202.group7.entity.Pet;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BookServiceMapper {
    @Insert("INSERT INTO `cpt202-group7`.`order` (`orderId`, `userId`, `createTime`, `petId`, `totalPrice`, `state`, `startTime`,`endTime`) VALUES (#{orderId}, #{userId}, #{createTime}, #{petId}, #{totalPrice}, #{state}, #{startTime},#{endTime})")
    void insertOrder(Order order);


    @Insert("INSERT INTO `cpt202-group7`.`appointment` (`serviceId`, `groomerId`,`orderId`) VALUES (#{serviceId},#{groomerId},#{orderId})")
    void insertAppointment(Appointment appointment);


    @Update("UPDATE order SET name = #{name}, age = #{age}, sex = #{sex}, tips = #{tips}, petTypeId = #{petTypeId}, size = #{size} WHERE petId = #{petId}")
    void updateOrderState(Order order);

}
