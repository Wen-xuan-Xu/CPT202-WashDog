package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO user(username, password, gender,nickname,phone,role) VALUES(#{username}, #{password}, #{gender}, #{nickname}, #{phone},'CUSTOMER')")
    void saveUser(User user);


    @Select("SELECT user_id FROM user WHERE username = #{username}")
    int getCurrentUserID(String username);

}


