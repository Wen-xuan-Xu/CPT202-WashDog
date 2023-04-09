package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO users(username, password, gender,nickname,phonenumber,role) VALUES(#{username}, #{password}, #{gender}, #{nickname}, #{phonenumber},'CUSTOMER')")
    void saveUser(User user);


}


