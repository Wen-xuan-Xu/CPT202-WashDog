package com.cpt202.group7.service;

import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.mapper.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetMapper petMapper;

    @Autowired
    private UserService userService;


    @Transactional
    public List<Pet> getPetList(){
        List<Pet> pets = new ArrayList<>();
        pets = petMapper.showpet(userService.getCurrentUserID());
        for (Pet pe: pets){
            pe.setType(petMapper.GetType(pe.getPetTypeId()));
        }
     return pets;
    }

    public void DeletePetimp(Integer petId){
        petMapper.DeletePet(petId);
    }

    public Pet GetPet(Integer petId){
        Pet pet = petMapper.GetPet(petId);
        pet.setType(petMapper.GetType(pet.getPetTypeId()));
        System.out.println(pet.getType());

        return pet;
    }

    public void UpdatePetimp(Pet pet, Integer petId){
        pet.setPetTypeId(petMapper.GetTypeId(pet.getType()));
        System.out.println(pet);
        petMapper.updatePet(pet);
    }


    public void InsertPet(Pet pet, Integer userId){
        pet.setPetTypeId(petMapper.GetTypeId(pet.getType()));
        pet.setUserId(userId);
        petMapper.InsertPet(pet);

    }
}
