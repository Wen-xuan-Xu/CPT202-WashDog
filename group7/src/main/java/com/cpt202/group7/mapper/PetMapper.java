package com.cpt202.group7.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.entity.User;
import jakarta.servlet.http.PushBuilder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PetMapper extends BaseMapper<Pet> {

}
