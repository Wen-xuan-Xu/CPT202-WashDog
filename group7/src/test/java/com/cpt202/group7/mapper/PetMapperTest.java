package com.cpt202.group7.mapper;


import com.cpt202.group7.entity.Pet;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@Rollback

class PetMapperTest {

    @Autowired
    private PetMapper petMapper;

    @Test
    void getPetList() {
        List<Pet> pets = petMapper.getPetList(53);
        Assertions.assertEquals(2, pets.size());
        Assertions.assertEquals("man", pets.get(0).getName());
    }

    @Test
    void getPet() {
        Pet pet = petMapper.getPet(1);
        Assertions.assertEquals("man", pet.getName());
        Assertions.assertEquals(53, pet.getUserId());
    }

    @Test
    void getTypeId() {
        Integer typeId = petMapper.getTypeId("Poodle");
        Assertions.assertEquals(7, typeId);
    }

    @Test
    void getType() {
        String type = petMapper.getType(1);
        Assertions.assertEquals("Siberian Husky", type);
    }

    @Test
    void getIconURL() {
        String iconURL = petMapper.getIconURL(1);
        Assertions.assertEquals("/img/petTypeicon/Siberian Husky.png", iconURL);
    }

    @Test
    void updatePet() {
        Pet pet = new Pet();
        pet.setPetId(1);
        pet.setAge(2);
        pet.setName("lya");
        pet.setPetTypeId(1);
        pet.setUserId(53);
        pet.setSex("male");
        pet.setTips("asd");
        pet.setSize("mid");
        petMapper.updatePet(pet);
        Pet updatedPet = petMapper.getPet(1);
        Assertions.assertEquals("lya", updatedPet.getName());
        Assertions.assertEquals(2, updatedPet.getAge());
    }

    @Test
    void insertPet() {
        Pet pet = new Pet();
        pet.setAge(2);
        pet.setName("lya");
        pet.setPetTypeId(1);
        pet.setUserId(53);
        pet.setSex("male");
        pet.setTips("asd");
        pet.setSize("mid");
        petMapper.insertPet(pet);
        Assertions.assertEquals(4, petMapper.getPetList(53).size());
    }

    @Test
    void deletePet() {
        petMapper.deletePet(1);
        Pet deletedPet = petMapper.getPet(1);
        Assertions.assertNull(deletedPet);
    }

    @Test
    void getAllPetType() {
        List<String> types = petMapper.getAllPetType();
        Assertions.assertEquals(32, types.size());
        Assertions.assertEquals("Siberian Husky", types.get(0));
    }
}