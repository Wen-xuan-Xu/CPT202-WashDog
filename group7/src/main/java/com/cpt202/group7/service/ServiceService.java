package com.cpt202.group7.service;

import com.cpt202.group7.mapper.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {
    @Autowired
    private ServiceMapper serviceMapper;


    public List<com.cpt202.group7.entity.Service> getServicesByPetTypeID(Integer petTypeID) {
        return serviceMapper.getServicesByPetTypeID(petTypeID);
    }
}
