package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.Customer;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CustomerMapper {
    @Insert("INSERT INTO user(username, password, gender,nickname,phonenumber) VALUES (#{username}, #{password}, #{gender}, #{nickname}, #{phonenumber})")
    void insertOne(
            @Param("username")String username,
            @Param("password")String password,
            @Param("nickname")String nickname,
            @Param("gender")String gender,
            @Param("phonenumber")String phone
    );

    @Select("SELECT * FROM user WHERE username = #{username}")
    Customer findByEmail(String username);

}
