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


    // Get User's Pets According To the User's ID
    @Transactional
    public List<Pet> getPetList(){
        List<Pet> pets = petMapper.getPetList(userService.getCurrentUserID());
        for (Pet pet: pets){
            pet.setType(petMapper.getType(pet.getPetTypeId()));
            pet.setIconURL(petMapper.getIconURL(pet.getPetTypeId()));
        }
     return pets;
    }

    // Delete User's Pets According To the Pet's ID
    public void deletePet(Integer petId){
        petMapper.deletePet(petId);
    }


    // Get User's Pets According To the Pet's ID
    public Pet getPet(Integer petId){
        Pet pet = petMapper.getPet(petId);
        pet.setType(petMapper.getType(pet.getPetTypeId()));
        pet.setIconURL(petMapper.getIconURL(pet.getPetTypeId()));
        return pet;
    }

    public void updatePet(Pet pet, Integer petId){
        pet.setPetTypeId(petMapper.getTypeId(pet.getType()));
        pet.setIconURL(petMapper.getIconURL(pet.getPetTypeId()));
        petMapper.updatePet(pet);
    }


    public void insertPet(Pet pet, Integer userId){
        pet.setPetTypeId(petMapper.getTypeId(pet.getType()));
        pet.setUserId(userId);
        pet.setIconURL(petMapper.getIconURL(pet.getPetTypeId()));
        petMapper.insertPet(pet);
    }

    public List<String> getAllPetType(){
        return petMapper.getAllPetType();
    }
}
