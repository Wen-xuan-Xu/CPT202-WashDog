package com.cpt202.group7.service;

import com.cpt202.group7.entity.Groomer;
import com.cpt202.group7.mapper.GroomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroomerService {
    @Autowired
    private GroomerMapper groomerMapper;

    public List<Groomer> getGroomersByServiceID(Integer serviceID){
        return groomerMapper.getGroomersByServiceID(serviceID);
    }

}
