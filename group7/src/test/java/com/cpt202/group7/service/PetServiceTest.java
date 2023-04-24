package com.cpt202.group7.service;

import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.mapper.PetMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class PetServiceTest {

    @Mock
    private PetMapper petMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private PetService petService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getPetList() {
        when(userService.getCurrentUserID()).thenReturn(1);

        // create some dummy data to return from the petMapper.getPetList() method
        Pet pet1 = new Pet();
        pet1.setPetId(1);
        pet1.setName("Tom");
        Pet pet2 = new Pet();
        pet2.setPetTypeId(2);
        pet2.setName("Jerry");
        List<Pet> pets = Arrays.asList(pet1, pet2);

        // mock the petMapper.getPetList() method to return the dummy data
        when(petMapper.getPetList(1)).thenReturn(pets);

        // call the method being tested
        List<Pet> result = petService.getPetList();

        // assert that the correct data was returned
        Assertions.assertEquals(pets.size(), result.size());
        assertThat(pets.get(0)).isEqualTo(result.get(0));
        assertThat(pets.get(1)).isEqualTo(result.get(1));


        // verify that the petMapper.getPetList() and userService.getCurrentUserID() methods were called
        verify(petMapper).getPetList(1);
        verify(userService).getCurrentUserID();
    }

    @Test
    void deletePet() {
        Pet pet = new Pet();
        pet.setPetId(1);
        pet.setName("Tom");
        petService.deletePet(1);
        verify(petMapper).deletePet(1);



    }

    @Test
    void getPet() {
        Pet pet1 = new Pet();
        pet1.setPetId(1);
        pet1.setName("Tom");
        when(petMapper.getPet(1)).thenReturn(pet1);
        Pet petResult = petService.getPet(1);
        assertThat(petResult).isEqualTo(pet1);
        verify(petMapper).getPet(1);

    }

    @Test
    void updatePet() {
        Pet pet = new Pet();
        pet.setPetId(1);
        pet.setName("Miaomiao");
        pet.setType("Dog");
        pet.setAge(3);
        pet.setSex("Male");
        pet.setTips("A cute dog");

        when(petMapper.getTypeId(any())).thenReturn(1);
        when(petMapper.getIconURL(any())).thenReturn("https://iconurl.com");

        petService.updatePet(pet,1);





    }

    @Test
    void insertPet() {
    }

    @Test
    void getAllPetType() {
    }
}