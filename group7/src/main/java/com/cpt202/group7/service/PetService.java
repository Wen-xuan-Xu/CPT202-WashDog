package com.cpt202.group7.service;

import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.mapper.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetMapper petMapper;

    @Autowired
    private UserService userService;


    @Transactional
    public List<Pet> getPetList(){
        return petMapper.selectList(null);
    }
}
