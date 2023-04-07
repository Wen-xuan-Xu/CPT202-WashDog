package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.Customer;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CustomerMapper {
    @Insert("INSERT INTO user(username, password, gender,nickname) VALUES (#{username}, #{password}, #{gender}, #{nickname})")
    void save(@Param("username")String username,@Param("password")String password,@Param("gender")String gender,@Param("nickname")String nickname);

    @Select("SELECT * FROM user WHERE username = #{username}")
    Customer findByEmail(String username);

}
