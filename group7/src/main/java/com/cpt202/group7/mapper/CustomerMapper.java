package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper {
    @Insert("INSERT INTO users (email, password, gender) VALUES (#{email}, #{password}, #{gender})")
    void save(Customer user);

    @Select("SELECT * FROM users WHERE email = #{email}")
    Customer findByEmail(String email);
}
