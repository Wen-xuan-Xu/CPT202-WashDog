package com.cpt202.group7.service;

import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.mapper.PetMapper;
import com.cpt202.group7.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    @Autowired
    private PetMapper petMapper;

    @Autowired
    private UserService userService;

    public void insertPet(Pet pet,String type){
        pet.setUser_id(userService.getCurrentUserID());
        pet.setPet_type_id(petMapper.findPetTypeID(type));

        petMapper.insertPet(pet);
    }
}
