package com.cpt202.group7.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.mapper.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetMapper petMapper;

    @Autowired
    private UserService userService;

//    public void insertPet(Pet pet,String type){
//        pet.setUser_id(userService.getCurrentUserID());
//        pet.setPet_type_id(petMapper.findPetTypeID(type));
//
//        petMapper.insertPet(pet);
//    }

}
