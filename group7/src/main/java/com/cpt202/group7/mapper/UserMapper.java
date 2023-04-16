package com.cpt202.group7.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);
    @Insert("INSERT INTO user(username, password, gender,nickname,phone,role) VALUES(#{username}, #{password}, #{gender}, #{nickname}, #{phone},'CUSTOMER')")
    void saveUser(User user);


    @Select("SELECT userId FROM user WHERE username = #{username}")
    int getCurrentUserID(String username);

    @Select("SELECT photo FROM user WHERE username = #{username}")
    String getCurrentUserPhoto(String username);

}


