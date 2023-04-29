package com.cpt202.group7.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpt202.group7.entity.Groomer;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;


@Mapper
public interface GroomerMapper extends BaseMapper<Groomer> {
    @Select("select * FROM groomer")
    List<Groomer> getGroomerList();

    @Select("SELECT * FROM groomer WHERE groomerId = #{groomerId}")
    Groomer getGroomer(Integer groomerId);

    @Update("UPDATE pet SET name = #{name}, gender = #{gender}, age = #{age}, groomerStarLevelPriceCoefficientId = #{groomerStarLevelPriceCoefficientId}, selfIntroduction = #{selfIntroduction}, workStartTime = #{workStartTime},workEndTime = #{workEndTime} ,isWorking = #{isWorking}WHERE groomerId = #{groomerId}")
    void updateGroomer(Groomer groomer, Integer groomerId);

    @Insert("INSERT INTO `cpt202-group7`.`groomer` (`name`, `gender`, `age`, `groomerStarLevelPriceCoefficientId`, `selfIntroduction`, `workStartTime`, `workEndTime`,`isWorking`) VALUES (#{name}, #{gender}, #{age}, #{groomerStarLevelPriceCoefficientId}, #{selfIntroduction}, #{workStartTime}, #{workEndTime},#{isWorking})")
    void insertGroomer(Groomer groomer);

    @Delete("DELETE FROM `cpt202-group7`.`groomer` WHERE `groomerId` = #{groomerId}")
    void deleteGroomer(Integer groomerId);

    @Select("SELECT * FROM groomer WHERE groomerId IN (SELECT groomerId FROM groomer_service WHERE serviceID = #{serviceID}) ")
    List<Groomer> getGroomersByServiceID(Integer serviceID);

    @Select("""
            SELECT * FROM groomer WHERE groomerId in (SELECT groomer.groomerId
                                                       FROM groomer
                                                                INNER JOIN pet_Groomer ON groomer.groomerId = pet_Groomer.groomerId
                                                       WHERE pet_Groomer.petTypeId = #{petTypeID}
                                                         AND time(#{passInStartTime}) BETWEEN groomer.workStartTime AND groomer.workEndTime
                                                         AND groomer.isWorking = 1
                                                         AND groomer.groomerId NOT IN (
                                                           SELECT appointment.groomerId
                                                           FROM appointment
                                                                    INNER JOIN `order` ON appointment.orderId = `order`.orderId
                                                           WHERE DATE(`order`.startTime) = DATE(#{passInStartTime})
                                                           AND `order`.state!=('CANCELED')
                                                       )
                                                       GROUP BY groomer.groomerId
                                                       HAVING COUNT(*) < 8);""")
    List<Groomer> getGroomerListByTheDate(Timestamp passInStartTime, Integer petTypeID);
}
