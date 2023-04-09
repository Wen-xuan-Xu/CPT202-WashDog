package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface CustomerLoginMapper {
    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User getInfo(@Param("username") String username, @Param("password") String password);
}
