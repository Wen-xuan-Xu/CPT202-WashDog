package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.Groomer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroomerMapper {
    @Select("SELECT * FROM groomer WHERE groomerId IN (SELECT groomerId FROM groomer_service WHERE serviceID = #{serviceID}) ")
    List<Groomer> getGroomersByServiceID(Integer serviceID);

}
